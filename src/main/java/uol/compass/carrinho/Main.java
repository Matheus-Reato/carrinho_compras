package uol.compass.carrinho;

import uol.compass.carrinho.enums.Categoria;
import uol.compass.carrinho.model.Carrinho;
import uol.compass.carrinho.model.Estoque;


public class Main {
    public static void main(String[] args) {

        Estoque estoque = new Estoque(1, "Tenis",Categoria.CALCADO, 299.0,3);

        Carrinho carrinho = new Carrinho(1, "Tenis", Categoria.CALCADO, 299.0, 1, 299.0);

        System.out.println(estoque);
        System.out.println(carrinho);
    }
}