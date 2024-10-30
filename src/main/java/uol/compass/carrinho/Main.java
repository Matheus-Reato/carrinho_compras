package uol.compass.carrinho;

import uol.compass.carrinho.enums.Categoria;
import uol.compass.carrinho.model.dao.CarrinhoDao;
import uol.compass.carrinho.model.dao.DaoFactory;
import uol.compass.carrinho.model.dao.EstoqueDao;
import uol.compass.carrinho.model.dao.ItensCarrinhoDao;
import uol.compass.carrinho.model.entities.Carrinho;
import uol.compass.carrinho.model.entities.Estoque;
import uol.compass.carrinho.model.entities.ItensCarrinho;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        EstoqueDao estoqueDao = DaoFactory.createEstoqueDao();
        CarrinhoDao carrinhoDao = DaoFactory.createCarrinhoDao();
        ItensCarrinhoDao itensCarrinhoDao = DaoFactory.createItensCarrinhoDao();

        System.out.println("O que você deseja gerenciar?");
        System.out.println("1 - Estoque");
        System.out.println("2 - Carrinho de compras");

        int entrada = sc.nextInt(); // fazer loop até inserir uma entrada válida

        if(entrada == 1){
            System.out.println("1 - Adicionar produto ao estoque");
            System.out.println("2 - Atualizar produto do estoque");
            System.out.println("3 - Remover produto do estoque");
            System.out.println("4 - Procurar produto do estoque por id");
            System.out.println("5 - Procurar todos os produtos do estoque");
            System.out.println("0 - Sair");

            entrada = sc.nextInt(); //fazer loop até inserir uma entrada válida
            sc.nextLine();

            if(entrada == 0){

            }

            if(entrada == 1){
                System.out.print("Nome do produto: ");
                String nome = sc.nextLine();

                System.out.print("Categoria (CAMISETA/CALCA/CALCADO): "); //fazer algo para ficar em loop até ser uma das categorias
                String categoria = sc.nextLine().toUpperCase();

                System.out.print("Valor: "); //valor não pode ser 0, ver onde fazer a verificação
                Double valor = sc.nextDouble();

                System.out.print("Quantidade: "); // quantidade não pode ser 0, mesma coisa do valor
                int quantidade = sc.nextInt();

                Estoque estoque = new Estoque(null, nome, Categoria.valueOf(categoria), valor, quantidade);
                estoqueDao.inserir(estoque);
                System.out.println("Produto inserido no estoque com sucesso!");
            }

            if(entrada == 2){
                System.out.print("Qual o id do produto que deseja alterar? ");
                int id = sc.nextInt();

                Estoque estoque = estoqueDao.encontrarPorId(id);

                System.out.print("Nome atual do produto = " + estoque.getNome() + ". Deseja alterá-lo?(s/n) ");
                char escolha = sc.next().charAt(0); // fazer loop até ser escolhido s/n
                if(escolha == 's'){
                    System.out.print("Novo nome do produto: ");
                    String nome = sc.next();
                    estoque.setNome(nome);
                }

                System.out.print("Nome atual da categoria = " + estoque.getCategoria() + ". Deseja alterá-lo?(s/n) ");
                escolha = sc.next().charAt(0); // fazer loop até ser escolhido s/n
                if(escolha == 's'){
                    System.out.print("Novo nome da categoria: ");
                    String categoria = sc.next();
                    estoque.setCategoria(Categoria.valueOf(categoria));
                }

                System.out.print("Valor atual do produto = " + estoque.getValor() + ". Deseja alterá-lo?(s/n) ");
                escolha = sc.next().charAt(0); // fazer loop até ser escolhido s/n
                if(escolha == 's'){
                    System.out.print("Novo valor do produto: ");
                    Double valor = sc.nextDouble();
                    estoque.setValor(valor);
                }

                System.out.print("Quantidade atual do produto = " + estoque.getQuantidade() + ". Deseja alterá-lo?(s/n) ");
                escolha = sc.next().charAt(0); // fazer loop até ser escolhido s/n
                if(escolha == 's'){
                    System.out.print("Nova quantidade do produto: ");
                    int quantidade = sc.nextInt();
                    estoque.setQuantidade(quantidade);
                }

                estoqueDao.atualizar(estoque);
                System.out.println("Produto atualizado com sucesso!");

            }


        }

        System.out.println("Saindo do if");


    }
}