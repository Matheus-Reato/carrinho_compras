package uol.compass.carrinho.model;

import uol.compass.carrinho.enums.Categoria;

public class ItensCarrinho{

    private Integer id;
    private Estoque produto;
    private Integer quantidade;

    public ItensCarrinho(Integer id, Estoque produto, Integer quantidade) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Estoque getProduto() {
        return produto;
    }

    public void setProduto(Estoque produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "ItensCarrinho{" +
                "id=" + id +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                '}';
    }
}
