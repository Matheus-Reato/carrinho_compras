package uol.compass.carrinho.modelo.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Carrinho {

    private Integer id;
    List<ItensCarrinho> itens = new ArrayList<>();
    private Double valor_total;

    public Carrinho() {
    }

    public Carrinho(Integer id) {
        this.id = id;
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

    public void adicionarItem(ItensCarrinho item) {
        itens.add(item);
        atualizarValorTotal();
    }

    private void atualizarValorTotal() {
        if(this.valor_total == null){
            this.valor_total = 0.0;
        }
        this.valor_total += itens.stream()
                .mapToDouble(ItensCarrinho::valorProduto)
                .sum();
    }

    public void atualizarValorBanco(List<ItensCarrinho> listaItens){

        this.valor_total = listaItens.stream().mapToDouble(ItensCarrinho::valorProduto).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrinho carrinho = (Carrinho) o;
        return Objects.equals(id, carrinho.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Carrinho{" +
                "ID= " + id +
                ", Valor Total= R$" + valor_total +
                '}';
    }
}
