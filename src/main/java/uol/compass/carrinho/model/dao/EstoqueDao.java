package uol.compass.carrinho.model.dao;

import uol.compass.carrinho.model.Estoque;

import java.util.List;

public interface EstoqueDao {

    void inserir(Estoque obj);
    void atualizar(Estoque obj);
    void deletarPorId(Integer id);
    Estoque encontrarPorId(Integer id);
    List<Estoque> encontrarTodos();
}
