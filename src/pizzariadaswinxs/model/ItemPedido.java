package pizzariadaswinxs.model;
import java.math.BigDecimal;

public class ItemPedido {
    private Produto produto;
    private int quantidade;

    public ItemPedido(Produto produto, int quantidade){
        this.produto = produto;
        this.quantidade = quantidade;

    }
    public Produto getProduto(){
        return produto;
    }
    public int getQuantidade(){
        return quantidade;
    }
    public void adicionarQuantidade(int quantidade){
        this.quantidade += quantidade;
    }
    public BigDecimal calcularSubtotalDoItem(){
        BigDecimal quantidadeConvertida = BigDecimal.valueOf(quantidade);
        BigDecimal subtotal = quantidadeConvertida.multiply(produto.getPreco());
        return subtotal;
    }

}