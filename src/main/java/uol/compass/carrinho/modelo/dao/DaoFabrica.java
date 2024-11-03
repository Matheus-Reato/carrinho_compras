package uol.compass.carrinho.modelo.dao;

import uol.compass.carrinho.DB.DB;
import uol.compass.carrinho.modelo.dao.impl.CarrinhoDaoJDBC;
import uol.compass.carrinho.modelo.dao.impl.EstoqueDaoJDBC;
import uol.compass.carrinho.modelo.dao.impl.ItensCarrinhoDaoJDBC;

public class DaoFabrica {

    public static CarrinhoDao criarCarrinhoDao() {
        return new CarrinhoDaoJDBC(DB.getConnection());
    }

    public static EstoqueDao criarEstoqueDao() {
        return new EstoqueDaoJDBC(DB.getConnection());
    }

    public static ItensCarrinhoDao criarItensCarrinhoDao() {
        return new ItensCarrinhoDaoJDBC(DB.getConnection());
    }
}
