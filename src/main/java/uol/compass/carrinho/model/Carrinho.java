package uol.compass.carrinho.model;


import uol.compass.carrinho.enums.Categoria;

public class Carrinho {

    private Integer id;
    private String nome;
    private Categoria categoria;
    private Double valor;
    private int quantidade;
    private Double valor_total;

    public Carrinho(Integer id, String nome, Categoria categoria, Double valor, int quantidade, Double valor_total) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
        this.quantidade = quantidade;
        this.valor_total = valor_total;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
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
                ", nome='" + nome + '\'' +
                ", categoria=" + categoria +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", valor_total=" + valor_total +
                '}';
    }
}
