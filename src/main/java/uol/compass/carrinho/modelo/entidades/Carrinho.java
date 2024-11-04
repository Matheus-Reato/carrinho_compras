package uol.compass.carrinho.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

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
    public String toString() {
        return "Carrinho{" +
                "ID= " + id +
                ", Valor Total= R$" + valor_total +
                '}';
    }
}
