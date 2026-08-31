package pizzariadaswinxs.controller;

import pizzariadaswinxs.model.Bebida;
import pizzariadaswinxs.model.Combo;
import pizzariadaswinxs.model.ItemPedido;
import pizzariadaswinxs.model.Pizza;
import pizzariadaswinxs.model.Preferencias;
import pizzariadaswinxs.model.Produto;
import pizzariadaswinxs.model.Sobremesa;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class MenuController {

    private final Scanner scanner;

    private final List<Pizza> pizzas = List.of(
            new Pizza("Calabresa G ", new BigDecimal("35.00"), "Salgada"),
            new Pizza("Frango com Catupiry G", new BigDecimal("40.00"), "Salgada"),
            new Pizza("Quatro Queijos G", new BigDecimal("42.00"), "Salgada"),
            new Pizza("Portuguesa G", new BigDecimal("40.00"), "Salgada"),
            new Pizza("Chocolate G", new BigDecimal("38.00"), "Doce"),
            new Pizza("Banana com Canela G", new BigDecimal("35.00"), "Doce")
    );

    private final List<Bebida> bebidas = List.of(
            new Bebida("Coca-Cola 1 Litro", new BigDecimal("8.00"), "Refrigerante"),
            new Bebida("Antarctica 1 litro", new BigDecimal("7.00"), "Refrigerante"),
            new Bebida("Suco de Maracujá 700 ml", new BigDecimal("8.00"), "Suco"),
            new Bebida("Água 500 ml", new BigDecimal("5.00"), "Água")
    );

    private final List<Sobremesa> sobremesas = List.of(
            new Sobremesa("Pudim 100 gr", new BigDecimal("10.00")),
            new Sobremesa("Romeu e Julieta 100 gr", new BigDecimal("10.00")),
            new Sobremesa("Banana com Canela 100 gr", new BigDecimal("10.00"))
    );

    private final Combo combo = new Combo("Combo Pizza + Refrigerante 1 litro", new BigDecimal("45.00"));

    public MenuController(Scanner scanner) {
        this.scanner = scanner;
    }

    // ============================================================
    // CARDÁPIO
    // ============================================================

    public void exibirCardapio() {
        System.out.println("\n================================= CARDÁPIO =================================");
        System.out.printf("%-32s | %-25s | %-20s%n", "PIZZAS", "BEBIDAS", "SOBREMESAS");
        System.out.println("--------------------------------+---------------------------+--------------------");

        exibirLinhaCardapio(0);

        System.out.println();
        System.out.println("Combo: " + combo.getNome() + " - R$ " + combo.getPreco());
    }

    private void exibirLinhaCardapio(int indice) {

        boolean temPizza = indice < pizzas.size();
        boolean temBebida = indice < bebidas.size();
        boolean temSobremesa = indice < sobremesas.size();

        if (!temPizza && !temBebida && !temSobremesa) {
            return;
        }

        String colunaPizza = temPizza ? (indice + 1) + " - " + pizzas.get(indice).getNome() +" "+ pizzas.get(indice).getPreco() : "";
        String colunaBebida = temBebida ? (indice + 1) + " - " + bebidas.get(indice).getNome() +" "+ bebidas.get(indice).getPreco(): "";
        String colunaSobremesa = temSobremesa ? (indice + 1) + " - " + sobremesas.get(indice).getNome() + " " + sobremesas.get(indice).getPreco(): "";

        System.out.printf("%-32s | %-25s | %-20s%n", colunaPizza, colunaBebida, colunaSobremesa);

        exibirLinhaCardapio(indice + 1);
    }

    // ============================================================
    // ESCOLHA DE PRODUTO
    // ============================================================

    /**
     * @return o item escolhido pelo cliente, ou null se ele cancelar.
     */
    public ItemPedido escolherProduto() {

        System.out.println("\nO que você deseja escolher?");
        System.out.println("1 - Pizza");
        System.out.println("2 - Bebida");
        System.out.println("3 - Sobremesa");
        System.out.println("4 - Combo Pizza + Refrigerante");
        System.out.println("0 - Finalizar pedido");
        System.out.print("Digite o número correspondente: ");

        String opcao = scanner.nextLine();

        return switch (opcao) {
            case "1" -> escolherDaLista(pizzas, "pizza");
            case "2" -> escolherDaLista(bebidas, "bebida");
            case "3" -> escolherDaLista(sobremesas, "sobremesa");
            case "4" -> escolherCombo();
            case "0" -> null;
            default -> {
                System.out.println("Opção inválida.");
                yield escolherProduto();
            }
        };
    }

    private <T extends Produto> ItemPedido escolherDaLista(List<T> produtos, String rotulo) {

        System.out.println();
        exibirOpcoes(produtos, 0);
        System.out.println("0 - Cancelar");
        System.out.print("Escolha a " + rotulo + " (número): ");

        String opcao = scanner.nextLine();

        if (opcao.equals("0")) {
            return null;
        }

        Produto escolhido = selecionarPorIndice(produtos, opcao);

        if (escolhido == null) {
            System.out.println("Opção inválida.");
            return escolherDaLista(produtos, rotulo);
        }

        int quantidade = perguntarQuantidade();

        return new ItemPedido(escolhido, quantidade);
    }

    private ItemPedido escolherCombo() {

        System.out.println();
        System.out.println(combo.getNome() + " - R$ " + combo.getPreco());
        System.out.println("1 - Adicionar");
        System.out.println("0 - Cancelar");
        System.out.print("Escolha: ");

        String opcao = scanner.nextLine();

        if (opcao.equals("1")) {
            int quantidade = perguntarQuantidade();
            return new ItemPedido(combo, quantidade);
        }

        if (opcao.equals("0")) {
            return null;
        }

        System.out.println("Opção inválida.");
        return escolherCombo();
    }

    private int perguntarQuantidade() {

        System.out.print("Quantidade: ");
        String entrada = scanner.nextLine();

        int quantidade;

        try {
            quantidade = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            System.out.println("Quantidade inválida.");
            return perguntarQuantidade();
        }

        if (quantidade <= 0) {
            System.out.println("A quantidade precisa ser maior que zero.");
            return perguntarQuantidade();
        }

        return quantidade;
    }

    // ============================================================
    // PREFERÊNCIAS (molho, talheres, observação)
    // ============================================================

    public Preferencias perguntarPreferencias() {

        System.out.print("Deseja molho? (S/N): ");
        boolean molho = scanner.nextLine().trim().equalsIgnoreCase("S");

        System.out.print("Precisa de talheres? (S/N): ");
        boolean talheres = scanner.nextLine().trim().equalsIgnoreCase("S");

        System.out.print("Deseja adicionar uma observação? ");
        String observacao = scanner.nextLine().trim();

        return new Preferencias(molho, talheres, observacao);
    }

    // ============================================================
    // AUXILIARES (recursivos — sem for/while)
    // ============================================================

    private void exibirOpcoes(List<? extends Produto> produtos, int indice) {

        if (indice >= produtos.size()) {
            return;
        }

        System.out.println((indice + 1) + " - " + produtos.get(indice).getNome());

        exibirOpcoes(produtos, indice + 1);
    }

    private Produto selecionarPorIndice(List<? extends Produto> produtos, String opcao) {

        int indice;

        try {
            indice = Integer.parseInt(opcao) - 1;
        } catch (NumberFormatException e) {
            return null;
        }

        if (indice < 0 || indice >= produtos.size()) {
            return null;
        }

        return produtos.get(indice);
    }
}