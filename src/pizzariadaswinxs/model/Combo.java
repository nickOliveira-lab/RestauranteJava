package pizzariadaswinxs.model;

import java.math.BigDecimal;

public class Combo extends Produto {

    public Combo(String nome, BigDecimal preco) {
        super(nome, preco, "Combo");
    }
}