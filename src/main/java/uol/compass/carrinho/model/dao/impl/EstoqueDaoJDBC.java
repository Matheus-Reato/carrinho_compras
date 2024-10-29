package uol.compass.carrinho.model.dao.impl;

import uol.compass.carrinho.DB.DB;
import uol.compass.carrinho.DB.DbException;
import uol.compass.carrinho.model.Estoque;
import uol.compass.carrinho.model.dao.EstoqueDao;

import java.sql.*;
import java.util.List;

public class EstoqueDaoJDBC implements EstoqueDao {

    private Connection conn;

    public EstoqueDaoJDBC(Connection conn){
        this.conn = conn;
    }
    @Override
    public void inserir(Estoque obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO estoque "
                            + "(nome, categoria, valor, quantidade) "
                            + "VALUES "
                            + "(?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());
            st.setString(2, obj.getCategoria().name());
            st.setDouble(3, obj.getValor());
            st.setInt(4, obj.getQuantidade());

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
