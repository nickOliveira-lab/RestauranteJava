package pizzariadaswinxs.model;
import java.math.BigDecimal;

public record ResultadoPagamento(
        boolean delivery,
        String bairro,
        String formaPagamento,
        BigDecimal taxaEntrega,
        BigDecimal desconto) {

}
