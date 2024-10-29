package uol.compass.carrinho.model.dao.impl;

import uol.compass.carrinho.DB.DB;
import uol.compass.carrinho.DB.DbException;
import uol.compass.carrinho.model.Estoque;
import uol.compass.carrinho.model.ItensCarrinho;
import uol.compass.carrinho.model.dao.ItensCarrinhoDao;

import java.sql.*;
import java.util.List;

public class ItensCarrinhoDaoJDBC implements ItensCarrinhoDao {

    private Connection conn;

    public ItensCarrinhoDaoJDBC(Connection conn){
        this.conn = conn;
    }
    @Override
    public void inserir(ItensCarrinho obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO itens_carrinho "
                            + "(carrinho_id, produto_id, quantidade) "
                            + "VALUES "
                            + "(?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getCarrinho_id());
            st.setInt(2, obj.getProduto().getId());
            st.setInt(3, obj.getQuantidade());

            int linhasAfetadas = st.executeUpdate();

            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }
            else {
                throw new DbException("Erro! Nenhuma linha foi afetada.");
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
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
