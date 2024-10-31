package uol.compass.carrinho.model.dao.impl;

import uol.compass.carrinho.DB.DB;
import uol.compass.carrinho.DB.DbException;
import uol.compass.carrinho.enums.Categoria;
import uol.compass.carrinho.enums.Status;
import uol.compass.carrinho.model.entities.Estoque;
import uol.compass.carrinho.model.dao.EstoqueDao;

import java.sql.*;
import java.util.ArrayList;
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
                            + "(nome, categoria, valor, quantidade, status_produto) "
                            + "VALUES "
                            + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());
            st.setString(2, obj.getCategoria().name());
            st.setDouble(3, obj.getValor());
            st.setInt(4, obj.getQuantidade());
            st.setString(5, obj.getStatus_produto().name());

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
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE estoque "
                            + "SET nome = ?, categoria = ?, valor = ?, quantidade = ?, status_produto = ? "
                            + "WHERE Id = ?");

            st.setString(1, obj.getNome());
            st.setString(2, obj.getCategoria().name());
            st.setDouble(3, obj.getValor());
            st.setInt(4, obj.getQuantidade());
            st.setString(5, obj.getStatus_produto().name());
            st.setInt(6, obj.getId());

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
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "DELETE FROM estoque WHERE id = ?");

            st.setInt(1, id);

            st.executeUpdate();
        }
        catch (SQLException e) {
            //trocar mais tarde por DbIntegrityException, precisa criar ou fazer algo equivalente
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Estoque encontrarPorId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM estoque WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Estoque obj = new Estoque();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCategoria(Categoria.valueOf(rs.getString("categoria")));
                obj.setValor(rs.getDouble("valor"));
                obj.setQuantidade(rs.getInt("quantidade"));
                obj.setStatus_produto(Status.valueOf(rs.getString("status_produto")));
                return obj;
            }

            throw new IllegalArgumentException("Este ID não existe");

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
    public List<Estoque> encontrarTodos() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM estoque ORDER BY categoria");
            rs = st.executeQuery();

            List<Estoque> list = new ArrayList<>();

            while (rs.next()) {
                Estoque obj = new Estoque();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCategoria(Categoria.valueOf(rs.getString("categoria")));
                obj.setValor(rs.getDouble("valor"));
                obj.setQuantidade(rs.getInt("quantidade"));
                obj.setStatus_produto(Status.valueOf(rs.getString("status_produto")));
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
