package uol.compass.carrinho.model.entities;

import uol.compass.carrinho.enums.Categoria;

public class Estoque {

    private Integer id;
    private String nome;
    private Categoria categoria;
    private Double valor;
    private Integer quantidade;

    public Estoque() {
    }

    public Estoque(Integer id, String nome, Categoria categoria, Double valor, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Estoque{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", categoria=" + categoria +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                '}';
    }
}
