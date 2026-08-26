package pizzariadaswinxs.model;

import java.math.BigDecimal;

public class Produto {

    private final String nome;
    private final BigDecimal preco;
    private final String categoria;

    public Produto(String nome, BigDecimal preco, String categoria) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getCategoria() {
        return categoria;
    }
}