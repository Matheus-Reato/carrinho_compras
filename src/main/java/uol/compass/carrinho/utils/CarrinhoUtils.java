package uol.compass.carrinho.utils;

import uol.compass.carrinho.model.dao.CarrinhoDao;
import uol.compass.carrinho.model.dao.EstoqueDao;
import uol.compass.carrinho.model.entities.Carrinho;
import uol.compass.carrinho.model.entities.Estoque;

import java.util.List;

public class CarrinhoUtils {

    public static void exibirListaCarrinhos(CarrinhoDao carrinhoDao) {
        List<Carrinho> listaCarrinho = carrinhoDao.encontrarTodos();
        for (Carrinho carrinho : listaCarrinho) {
            System.out.println(carrinho);
        }
    }
}
