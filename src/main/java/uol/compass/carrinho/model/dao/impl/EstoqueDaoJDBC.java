package uol.compass.carrinho.model.dao.impl;

import uol.compass.carrinho.model.Estoque;
import uol.compass.carrinho.model.dao.EstoqueDao;

import java.sql.Connection;
import java.util.List;

public class EstoqueDaoJDBC implements EstoqueDao {

    private Connection conn;

    public EstoqueDaoJDBC(Connection conn){
        this.conn = conn;
    }
    @Override
    public void inserir(Estoque obj) {

    }

    @Override
    public void atualizar(Estoque obj) {

    }

    @Override
    public void deletarPorId(Integer id) {

    }

    @Override
    public Estoque encontrarPorId(Integer id) {
        return null;
    }

    @Override
    public List<Estoque> encontrarTodos() {
        return null;
    }
}
