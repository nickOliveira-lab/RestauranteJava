package pizzariadaswinxs.model;

import java.math.BigDecimal;

public class Pizza extends Produto {

    private final String sabor;

    public Pizza(String sabor, BigDecimal preco, String categoria) {
        super(sabor, preco, categoria);
        this.sabor = sabor;
    }

    public String getSabor() {
        return sabor;
    }
}