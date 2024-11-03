package uol.compass.carrinho.utilidade;

import uol.compass.carrinho.modelo.dao.CarrinhoDao;
import uol.compass.carrinho.modelo.entidades.Carrinho;

import java.util.List;

public class CarrinhoUtil {

    public static void exibirListaCarrinhos(CarrinhoDao carrinhoDao) {
        List<Carrinho> listaCarrinho = carrinhoDao.encontrarTodos();
        for (Carrinho carrinho : listaCarrinho) {
            System.out.println(carrinho);
        }
    }
}
