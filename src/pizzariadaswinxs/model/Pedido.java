package pizzariadaswinxs.model;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

public class Pedido {
    private Cliente cliente;
    private List<ItemPedido> itens;
    private BigDecimal taxaEntrega;
    private BigDecimal desconto;

    public Pedido(Cliente cliente){
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.taxaEntrega = BigDecimal.ZERO;
        this.desconto = BigDecimal.ZERO;

    }

    public Cliente getCliente(){
        return cliente;

    }

    public List<ItemPedido> getItens() {
        return itens;

    }
    public BigDecimal getTaxaEntrega(){
        return taxaEntrega;

    }
    public BigDecimal getDesconto(){
        return desconto;

    }

    public void setTaxaEntrega(BigDecimal taxaEntrega){
        this.taxaEntrega = taxaEntrega;
    }
    public void setDesconto(BigDecimal desconto){
        this.desconto = desconto;
    }

    private ItemPedido buscarItem(Produto produto, int indice) {
        if (indice >= this.itens.size()) {
            return null;
        }
        ItemPedido itemAtual = this.itens.get(indice);
        if (itemAtual.getProduto() == produto){
            return itemAtual;
        }
        return buscarItem(produto,indice+1);
    }

    public void adicionarItem(Produto produto, int quantidade){
        ItemPedido itemEncontrado = buscarItem(produto,0);
        if (itemEncontrado != null){
            itemEncontrado.adicionarQuantidade(quantidade);
        }else {
            ItemPedido novoItem = new ItemPedido(produto, quantidade);
            this.itens.add(novoItem);
        }
    }

}
