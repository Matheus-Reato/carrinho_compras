package uol.compass.carrinho.model;

import java.util.List;

public class Carrinho {

    private Integer id;
    List<ItensCarrinho> itens;
    private Double valor_total;

    public Carrinho(Integer id, List<ItensCarrinho> itens, Double valor_total) {
        this.id = id;
        this.itens = itens;
        this.valor_total = valor_total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ItensCarrinho> getItens() {
        return itens;
    }

    public void setItens(List<ItensCarrinho> itens) {
        this.itens = itens;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

    @Override
    public String toString() {
        return "Carrinho{" +
                "id=" + id +
                ", itens=" + itens +
                ", valor_total=" + valor_total +
                '}';
    }
}
