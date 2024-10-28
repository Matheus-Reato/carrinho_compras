package uol.compass.carrinho.model.dao;

import uol.compass.carrinho.model.Carrinho;
import uol.compass.carrinho.model.Estoque;

import java.util.List;

public interface CarrinhoDao {

    void inserir(Carrinho obj);
    void atualizar(Carrinho obj);
    void deletarPorId(Integer id);
    Carrinho encontrarPorId(Integer id);
    List<Carrinho> encontrarTodos();
}
