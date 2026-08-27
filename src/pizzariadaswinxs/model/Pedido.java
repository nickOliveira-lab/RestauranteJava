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

    }

    public List<ItemPedido> getItens() {

    }
    public BigDecimal getTaxaEntrega(){

    }
    public BigDecimal getDesconto(){

    }
}
