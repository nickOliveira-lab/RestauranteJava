package pizzariadaswinxs.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class PagamentoService {

    private static final BigDecimal LIMITE_ENTREGA_GRATIS = new BigDecimal("100.00");
    private static final BigDecimal TAXA_ENTREGA_PADRAO = new BigDecimal("10.00");
    private static final BigDecimal LIMITE_DESCONTO  = new BigDecimal("150.00");
    private static final BigDecimal PERCENTUAL_DESCONTO = new BigDecimal("0.10");

    private static final List<String> BAIRROS_ATENTIDOS = List.of(
            "Santo Amaro",
            "Boa Vista",
            "Recife Antigo"
    );

    public boolean bairroAtendido(String bairro){
        return BAIRROS_ATENTIDOS.stream()
                .anyMatch(item -> item.equalsIgnoreCase(bairro));
    }
    public BigDecimal calcularEntrega(BigDecimal subtotal, boolean delivery) {

        if (!delivery) {
            return BigDecimal.ZERO;
        }

        if (subtotal.compareTo(LIMITE_ENTREGA_GRATIS) > 0) {
            return BigDecimal.ZERO;
        }

        return TAXA_ENTREGA_PADRAO;
    }

    public BigDecimal calcularDesconto(BigDecimal subtotal, String formaPagamento){
        boolean pagamentoPix = "PIX".equalsIgnoreCase(formaPagamento);
        boolean acimaDoLimite = subtotal.compareTo(LIMITE_DESCONTO) > 0;

        if (!pagamentoPix || !acimaDoLimite){
            return BigDecimal.ZERO;
        }
        return subtotal
                .multiply(PERCENTUAL_DESCONTO)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
