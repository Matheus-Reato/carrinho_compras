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

        /* TESTE INSERINDO ESTOQUE NO BANCO DE DADOS
        Estoque estoque = new Estoque(null, "Tenis",Categoria.CALCADO, 299.0,3);
        Estoque estoque = new Estoque(null, "Calca Jeans",Categoria.CALCA, 399.0,5);
        Estoque estoque = new Estoque(null, "Camiseta Lisa",Categoria.CAMISETA, 89.90,10);
        estoqueDao.inserir(estoque);
        System.out.println("Inserido! Novo id = " + estoque.getId());
        */

        /* TESTE PROCURANDO POR ID NO BANCO DE DADOS
        Estoque estoque = estoqueDao.encontrarPorId(1);
        System.out.println(estoque);
        */

        /* TESTE ATUALIZANDO PRODUTO NO BANCO DE DADOS
        Estoque estoque = estoqueDao.encontrarPorId(1);
        estoque.setNome("Camiseta Polo");
        estoque.setCategoria(Categoria.CAMISETA);
        estoque.setValor(499.0);
        estoque.setQuantidade(1);
        estoqueDao.atualizar(estoque);
        System.out.println("Produto atualizado!");
        */

        /* TESTE PROCURANDO TODOS NO BANCO DE DADOS
        List<Estoque> listaEstoque= new ArrayList<>();
        listaEstoque = estoqueDao.encontrarTodos();
        for(Estoque obj : listaEstoque){
            System.out.println(obj);
        }
        */

//        ItensCarrinho itensCarrinho = new ItensCarrinho(1, estoque, 1);
//        list.add(itensCarrinho);
//        estoque = new Estoque(1, "Calca Jeans",Categoria.CALCA, 239.0,3);
//        itensCarrinho = new ItensCarrinho(1, estoque, 1);
//        list.add(itensCarrinho);
//
//        Carrinho carrinho = new Carrinho(1, list, 538.0);
//
//        System.out.println(estoque);
//        System.out.println(itensCarrinho);
//        System.out.println(carrinho);
    }
}