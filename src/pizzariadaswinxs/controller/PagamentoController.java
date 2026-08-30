package pizzariadaswinxs.controller;

import pizzariadaswinxs.exception.AreaRestritaException;
import pizzariadaswinxs.model.ResultadoPagamento;
import pizzariadaswinxs.service.PagamentoService;

import java.math.BigDecimal;
import java.util.Scanner;

public class PagamentoController {

    private final Scanner scanner;
    private final PagamentoService pagamentoService;

    public PagamentoController(Scanner scanner) {
        this.scanner = scanner;
        this.pagamentoService = new PagamentoService();
    }

    public ResultadoPagamento iniciarPagamento(BigDecimal subtotal) {

        boolean delivery = perguntarModalidade();
        String bairro = null;

        if (delivery) {
            bairro = perguntarBairro();
        }

        String formaPagamento = perguntarFormaPagamento();

        BigDecimal taxaEntrega = pagamentoService.calcularEntrega(subtotal, delivery);
        BigDecimal desconto = pagamentoService.calcularDesconto(subtotal, formaPagamento);

        return new ResultadoPagamento(delivery, bairro, formaPagamento, taxaEntrega, desconto);
    }

    private boolean perguntarModalidade() {

        System.out.println("\n1 - Delivery");
        System.out.println("2 - Retirada");
        System.out.print("Escolha: ");

        String opcao = scanner.nextLine();

        return switch (opcao) {
            case "1" -> true;
            case "2" -> false;
            default -> {
                System.out.println("Opção inválida.");
                yield perguntarModalidade();
            }
        };
    }

    private String perguntarBairro() {

        System.out.println("\nBairros atendidos: Santo Amaro, Boa Vista, Recife Antigo");
        System.out.print("Digite o bairro: ");

        String bairro = scanner.nextLine().trim();

        if (!pagamentoService.bairroAtendido(bairro)) {
            throw new AreaRestritaException(bairro);
        }

        return bairro;
    }

    private String perguntarFormaPagamento() {

        System.out.println("-----+-----+--Pagamento--+-----+-----+-");
        System.out.println("|     1. PIX                          |");
        System.out.println("|     2. Cartão de Crédito             |");
        System.out.println("|     3. Cartão de Débito              |");
        System.out.println("|     4. Dinheiro                      |");
        System.out.println("|                                      |");
        System.out.println("|  Compras acima de R$150 e            |");
        System.out.println("|  pagamentos via PIX - 10% Desconto   |");
        System.out.println("|-----+-----+-----+-----+-----+-----+-|");
        System.out.print("\nEscolha a forma de pagamento: ");

        String opcao = scanner.nextLine();

        return switch (opcao) {
            case "1" -> "PIX";
            case "2" -> "Cartão de Crédito";
            case "3" -> "Cartão de Débito";
            case "4" -> "Dinheiro";
            default -> {
                System.out.println("Opção inválida.");
                yield perguntarFormaPagamento();
            }
        };
    }
}