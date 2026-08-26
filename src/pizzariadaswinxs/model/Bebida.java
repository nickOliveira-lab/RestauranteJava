package pizzariadaswinxs.model;

import java.math.BigDecimal;

public class Bebida extends Produto {

    private final String tipo;

    public Bebida(String nome, BigDecimal preco, String tipo) {
        super(nome, preco, "Bebida");
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}