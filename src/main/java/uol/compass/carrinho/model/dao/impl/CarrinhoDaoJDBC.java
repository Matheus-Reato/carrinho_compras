package uol.compass.carrinho.model.dao.impl;

import uol.compass.carrinho.model.Carrinho;
import uol.compass.carrinho.model.dao.CarrinhoDao;

import java.sql.Connection;
import java.util.List;

public class CarrinhoDaoJDBC implements CarrinhoDao {

    private Connection conn;

    public CarrinhoDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void inserir(Carrinho obj) {

    }

    @Override
    public void atualizar(Carrinho obj) {

    }

    @Override
    public void deletarPorId(Integer id) {

    }

    @Override
    public Carrinho encontrarPorId(Integer id) {
        return null;
    }

    @Override
    public List<Carrinho> encontrarTodos() {
        return null;
    }
}
