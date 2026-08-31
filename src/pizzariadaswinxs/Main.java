package pizzariadaswinxs;

import pizzariadaswinxs.controller.MenuController;
import pizzariadaswinxs.controller.PagamentoController;
import pizzariadaswinxs.exception.AreaRestritaException;
import pizzariadaswinxs.exception.PedidoInvalidoException;
import pizzariadaswinxs.model.Cliente;
import pizzariadaswinxs.model.ItemPedido;
import pizzariadaswinxs.model.Pedido;
import pizzariadaswinxs.model.ResultadoPagamento;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MenuController menu = new MenuController(scanner);
        PagamentoController pagamentoController = new PagamentoController(scanner);

        System.out.println("===== PIZZARIA DAS WINXS =====");

        String nomeCliente = perguntarNome(scanner);
        Cliente cliente = new Cliente(nomeCliente);
        Pedido pedido = new Pedido(cliente);

        menu.exibirCardapio();
        montarPedido(pedido, menu);

        try {
            pedido.validarParaFinalizacao();
        } catch (PedidoInvalidoException e) {
            System.out.println("\n" + e.getMessage());
            return;
        }

        BigDecimal subtotal = pedido.getSubtotal();
        ResultadoPagamento resultado = processarPagamento(pagamentoController, subtotal);

        pedido.setTaxaEntrega(resultado.taxaEntrega());
        pedido.setDesconto(resultado.desconto());

        System.out.println();
        pedido.mostrarResumo();
    }

    private static String perguntarNome(Scanner scanner) {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine().trim();
        if (nome.isBlank()) {
            System.out.println("Nome não pode ser vazio.");
            return perguntarNome(scanner);
        }
        return nome;
    }

    private static void montarPedido(Pedido pedido, MenuController menu) {
        ItemPedido item = menu.escolherProduto();
        if (item == null) {
            return;
        }
        pedido.adicionarItem(item.getProduto(), item.getQuantidade());
        montarPedido(pedido, menu);
    }

    private static ResultadoPagamento processarPagamento(PagamentoController pagamentoController, BigDecimal subtotal) {
        try {
            return pagamentoController.iniciarPagamento(subtotal);
        } catch (AreaRestritaException e) {
            System.out.println("\n" + e.getMessage());
            return processarPagamento(pagamentoController, subtotal);
        }
    }
}

