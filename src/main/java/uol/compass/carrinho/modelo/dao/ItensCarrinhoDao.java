package uol.compass.carrinho.modelo.dao;

import uol.compass.carrinho.modelo.entidades.Carrinho;
import uol.compass.carrinho.modelo.entidades.ItensCarrinho;

import java.util.List;

public interface ItensCarrinhoDao {

    void inserir(ItensCarrinho obj);
    void atualizar(ItensCarrinho obj);
    void deletarPorId(Integer id);
    ItensCarrinho encontrarPorId(Integer id);
    List<ItensCarrinho> encontrarTodos();

    List<ItensCarrinho> encontrarPorCarrinho(Carrinho carrinho);

}
