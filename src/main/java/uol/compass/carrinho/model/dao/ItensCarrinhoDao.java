package uol.compass.carrinho.model.dao;

import uol.compass.carrinho.model.entities.Carrinho;
import uol.compass.carrinho.model.entities.ItensCarrinho;

import java.util.List;

public interface ItensCarrinhoDao {

    void inserir(ItensCarrinho obj);
    void atualizar(ItensCarrinho obj);
    void deletarPorId(Integer id);
    ItensCarrinho encontrarPorId(Integer id);
    List<ItensCarrinho> encontrarTodos();

    List<ItensCarrinho> encontrarPorCarrinho(Carrinho carrinho);

}
