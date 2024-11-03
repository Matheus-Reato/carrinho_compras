package uol.compass.carrinho.utilidade;

import uol.compass.carrinho.modelo.dao.EstoqueDao;
import uol.compass.carrinho.modelo.entidades.Estoque;

import java.util.List;

public class EstoqueUtil {
    public static void exibirListaEstoque(EstoqueDao estoqueDao) {
        List<Estoque> listaEstoque = estoqueDao.encontrarTodos();
        for (Estoque estoque : listaEstoque) {
            System.out.println(estoque);
        }
    }
}
