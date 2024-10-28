package uol.compass.carrinho;

import uol.compass.carrinho.enums.Categoria;
import uol.compass.carrinho.model.Carrinho;
import uol.compass.carrinho.model.Estoque;
import uol.compass.carrinho.model.ItensCarrinho;
import uol.compass.carrinho.model.dao.DaoFactory;
import uol.compass.carrinho.model.dao.EstoqueDao;
import uol.compass.carrinho.model.dao.impl.EstoqueDaoJDBC;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        EstoqueDao estoqueDao = DaoFactory.createEstoqueDao();

        List<ItensCarrinho> list = new ArrayList<>();

        Estoque estoque = new Estoque(1, "Tenis",Categoria.CALCADO, 299.0,3);
        ItensCarrinho itensCarrinho = new ItensCarrinho(1, estoque, 1);
        list.add(itensCarrinho);
        estoqueDao.inserir(estoque);

        estoque = new Estoque(1, "Calca Jeans",Categoria.CALCA, 239.0,3);
        itensCarrinho = new ItensCarrinho(1, estoque, 1);
        list.add(itensCarrinho);

        Carrinho carrinho = new Carrinho(1, list, 538.0);

        System.out.println(estoque);
        System.out.println(itensCarrinho);
        System.out.println(carrinho);
    }
}