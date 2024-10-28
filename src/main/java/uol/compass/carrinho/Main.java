package uol.compass.carrinho;

import uol.compass.carrinho.enums.Categoria;
import uol.compass.carrinho.model.Carrinho;

public class Main {
    public static void main(String[] args) {
        Carrinho carrinho = new Carrinho(1, "Tenis", Categoria.CALCADO, 299.0, 1, 299.0);

        System.out.println(carrinho);
    }
}