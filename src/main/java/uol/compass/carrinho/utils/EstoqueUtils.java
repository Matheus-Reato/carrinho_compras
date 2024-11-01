package uol.compass.carrinho.utils;

import uol.compass.carrinho.model.dao.EstoqueDao;
import uol.compass.carrinho.model.entities.Estoque;

import java.util.List;

public class EstoqueUtils {
    public static void exibirListaEstoque(EstoqueDao estoqueDao) {
        List<Estoque> listaEstoque = estoqueDao.encontrarTodos();
        for (Estoque estoque : listaEstoque) {
            System.out.println(estoque);
        }
    }
}
