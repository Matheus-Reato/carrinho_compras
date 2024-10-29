package uol.compass.carrinho.model.dao.impl;

import uol.compass.carrinho.DB.DB;
import uol.compass.carrinho.DB.DbException;
import uol.compass.carrinho.model.Carrinho;
import uol.compass.carrinho.model.dao.CarrinhoDao;

import java.sql.*;
import java.util.List;

public class CarrinhoDaoJDBC implements CarrinhoDao {

    private Connection conn;

    public CarrinhoDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void inserir(Carrinho obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO carrinho "
                            + "(valor_total) "
                            + "VALUES "
                            + "(?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setDouble(1, obj.getValor_total() != null ? obj.getValor_total() : 0.0 );


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
