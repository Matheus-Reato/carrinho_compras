package uol.compass.carrinho.modelo.entidades;

import uol.compass.carrinho.enums.Categoria;
import uol.compass.carrinho.enums.Status;

import java.util.Objects;

public class Estoque {

    private Integer id;
    private String nome;
    private Categoria categoria;
    private Double valor;
    private Integer quantidade;
    private Status status_produto;

    public Estoque() {
    }

    public Estoque(Integer id, String nome, Categoria categoria, Double valor, Integer quantidade, Status status_produto) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
        this.quantidade = quantidade;
        this.status_produto = status_produto;
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

    public Status getStatus_produto() {
        return status_produto;
    }

    public void setStatus_produto(Status status_produto) {
        this.status_produto = status_produto;
    }

    public void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
        }
    }

    public void validarCategoria(String categoria) {
        boolean valido = false;
        if(categoria != null) {
            for (Categoria c : Categoria.values()) {
                if (c.name().equals(categoria)) {
                    valido = true;
                }
            }
        }
        if(categoria == null || valido == false) {
            throw new IllegalArgumentException("Categoria inválida! Escolha entre: CAMISETA, CALCA, CALCADO.");
        }
    }

    public void validarValor(Double valor) {
        if (valor == null || valor <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }
    }

    public void validarQuantidade(Integer quantidade) {
        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
    }

    public void validarStatus(String status_produto) {
        boolean valido = false;
        if(status_produto != null) {
            for (Status s : Status.values()) {
                if (s.name().equals(status_produto)) {
                    valido = true;
                }
            }
        }
        if(status_produto == null || valido == false) {
            throw new IllegalArgumentException("Status inválido! Escolha entre: DISPONIVEL ou INDISPONIVEL.");
        }
    }

    public void atualizarQuantidade(ItensCarrinho item){
        this.quantidade -= item.getQuantidade();

        if(this.quantidade < 0){
            throw new IllegalArgumentException("Quantidade em estoque é menor do que a selecionada.");
        }
    }

    public void verficarDisponibilidade(Estoque estoque){
        if (estoque.getStatus_produto() == Status.INDISPONIVEL ){
            throw new IllegalArgumentException("Não é possível adicionar um item indisponível ao carrinho.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estoque estoque = (Estoque) o;
        return Objects.equals(id, estoque.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Estoque[" +
                "ID= " + id +
                ", Nome= '" + nome + '\'' +
                ", Categoria= " + categoria +
                ", Valor= R$" + valor +
                ", Quantidade em estoque= " + quantidade +
                ", Status= " + status_produto +
                ']';
    }
}
