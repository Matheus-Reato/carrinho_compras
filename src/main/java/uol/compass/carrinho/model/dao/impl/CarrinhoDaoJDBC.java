package uol.compass.carrinho.model.dao.impl;

import uol.compass.carrinho.DB.DB;
import uol.compass.carrinho.DB.DbException;
import uol.compass.carrinho.enums.Categoria;
import uol.compass.carrinho.model.Carrinho;
import uol.compass.carrinho.model.Estoque;
import uol.compass.carrinho.model.dao.CarrinhoDao;

import java.sql.*;
import java.util.ArrayList;
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
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE carrinho "
                            + "SET valor_total = ? "
                            + "WHERE id = ?");

            st.setDouble(1, obj.getValor_total());
            st.setInt(2, obj.getId());

            st.executeUpdate();
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deletarPorId(Integer id) {

    }

    @Override
    public Carrinho encontrarPorId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM carrinho WHERE id = ?"); //fazer um inner join com itens_carrinho pra pegar todos os itens com o mesmo id
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Carrinho obj = new Carrinho();
                obj.setId(rs.getInt("id"));
                obj.setValor_total(rs.getDouble("valor_total"));

                return obj;
            }
            return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Carrinho> encontrarTodos() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM carrinho ORDER BY id");
            rs = st.executeQuery();

            List<Carrinho> list = new ArrayList<>();

            while (rs.next()) {
                Carrinho obj = new Carrinho();

                obj.setId(rs.getInt("id"));
                obj.setValor_total(rs.getDouble("valor_total"));
                list.add(obj);
            }
            return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
