package uol.compass.carrinho.model;

import uol.compass.carrinho.enums.Categoria;

public class ItensCarrinho{

    private Integer id;
    private Estoque produto;
    private Integer quantidade;
    private Integer carrinho_id;

    public ItensCarrinho() {
    }

    public ItensCarrinho(Integer id, Estoque produto, Integer quantidade, Integer carrinho_id) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.carrinho_id = carrinho_id;
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

    public Integer getCarrinho_id() {
        return carrinho_id;
    }

    public void setCarrinho_id(Integer carrinho_id) {
        this.carrinho_id = carrinho_id;
    }

    public Double valorProduto(){
        return produto.getValor() * quantidade;
    }

    @Override
    public String toString() {
        return "ItensCarrinho{" +
                "id=" + id +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", carrinho_id=" + carrinho_id +
                '}';
    }
}
