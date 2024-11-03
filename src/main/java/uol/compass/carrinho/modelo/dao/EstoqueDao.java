package uol.compass.carrinho.modelo.dao;

import uol.compass.carrinho.modelo.entidades.Estoque;

import java.util.List;

public interface EstoqueDao {

    void inserir(Estoque obj);
    void atualizar(Estoque obj);
    void deletarPorId(Integer id);
    Estoque encontrarPorId(Integer id);
    List<Estoque> encontrarTodos();
}
