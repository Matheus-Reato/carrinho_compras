package uol.compass.carrinho.model.dao.impl;

import uol.compass.carrinho.model.Estoque;
import uol.compass.carrinho.model.ItensCarrinho;
import uol.compass.carrinho.model.dao.ItensCarrinhoDao;

import java.sql.Connection;
import java.util.List;

public class ItensCarrinhoDaoJDBC implements ItensCarrinhoDao {

    private Connection conn;

    public ItensCarrinhoDaoJDBC(Connection conn){
        this.conn = conn;
    }
    @Override
    public void inserir(ItensCarrinho obj) {

    }

    @Override
    public void atualizar(ItensCarrinho obj) {

    }

    @Override
    public void deletarPorId(Integer id) {

    }

    @Override
    public Estoque encontrarPorId(Integer id) {
        return null;
    }

    @Override
    public List<ItensCarrinho> encontrarTodos() {
        return null;
    }
}
