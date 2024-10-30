package uol.compass.carrinho.model.dao.impl;

import uol.compass.carrinho.DB.DB;
import uol.compass.carrinho.DB.DbException;
import uol.compass.carrinho.enums.Categoria;
import uol.compass.carrinho.model.Carrinho;
import uol.compass.carrinho.model.Estoque;
import uol.compass.carrinho.model.ItensCarrinho;
import uol.compass.carrinho.model.dao.ItensCarrinhoDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE itens_carrinho "
                            + "SET carrinho_id = ?, produto_id = ?, quantidade = ? "
                            + "WHERE Id = ?");

            st.setInt(1, obj.getCarrinho_id());
            st.setInt(2, obj.getProduto().getId());
            st.setInt(3, obj.getQuantidade());
            st.setInt(4, obj.getId());

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
            st = conn.prepareStatement("DELETE FROM itens_carrinho WHERE id = ?");

            st.setInt(1, id);

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
    public ItensCarrinho encontrarPorId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT itens_carrinho.id AS item_id, "
                    + "itens_carrinho.quantidade AS item_quantidade, "
                    + "itens_carrinho.produto_id AS produto_id, "
                    + "itens_carrinho.carrinho_id AS carrinho_id, "
                    + "estoque.nome AS produto_nome, "
                    + "estoque.categoria AS produto_categoria, "
                    + "estoque.valor AS produto_valor, "
                    + "estoque.quantidade AS produto_quantidade "
                    + "FROM itens_carrinho "
                    + "JOIN estoque ON itens_carrinho.produto_id = estoque.id "
                    + "WHERE itens_carrinho.id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Estoque estoque = instanciarEstoque(rs);
                ItensCarrinho obj = instanciarItensCarrinho(rs, estoque);
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

    private Estoque instanciarEstoque(ResultSet rs) throws SQLException {
        Estoque estoque = new Estoque();
        estoque.setId(rs.getInt("produto_id"));
        estoque.setNome(rs.getString("produto_nome"));
        estoque.setCategoria(Categoria.valueOf(rs.getString("produto_categoria")));
        estoque.setValor(rs.getDouble("produto_valor"));
        estoque.setQuantidade(rs.getInt("produto_quantidade"));
        return estoque;
    }


    private ItensCarrinho instanciarItensCarrinho(ResultSet rs, Estoque estoque) throws SQLException {
        ItensCarrinho obj = new ItensCarrinho();
        obj.setId(rs.getInt("item_id"));
        obj.setProduto(estoque);
        obj.setQuantidade(rs.getInt("item_quantidade"));
        obj.setCarrinho_id(rs.getInt("carrinho_id"));

        return obj;
    }

    @Override
    public List<ItensCarrinho> encontrarTodos() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT itens_carrinho.id AS item_id, "
                            + "itens_carrinho.quantidade AS item_quantidade, "
                            + "itens_carrinho.produto_id AS produto_id, "
                            + "itens_carrinho.carrinho_id AS carrinho_id, "
                            + "estoque.nome AS produto_nome, "
                            + "estoque.categoria AS produto_categoria, "
                            + "estoque.valor AS produto_valor, "
                            + "estoque.quantidade AS produto_quantidade "
                            + "FROM itens_carrinho "
                            + "JOIN estoque ON itens_carrinho.produto_id = estoque.id "
                            + "ORDER BY item_id"
            );

            rs = st.executeQuery();

            List<ItensCarrinho> itens = new ArrayList<>();
            Map<Integer, Estoque> mapa = new HashMap<>();

            while (rs.next()) {

                Estoque estoque = mapa.get(rs.getInt("produto_id"));

                if (estoque == null) {
                    estoque = instanciarEstoque(rs);
                    mapa.put(rs.getInt("produto_id"), estoque);
                }

                ItensCarrinho obj = instanciarItensCarrinho(rs, estoque);
                itens.add(obj);
            }
            return itens;
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
    public List<ItensCarrinho> encontrarPorCarrinho(Carrinho carrinho) {
            List<ItensCarrinho> todosItens = encontrarTodos();

            return todosItens.stream().filter(item -> item.getCarrinho_id() == carrinho.getId()).collect(Collectors.toList());
    }
}
