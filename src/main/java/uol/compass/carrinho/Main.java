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

        int entradaEstoque = 1;
        int entradaCarrinho = 1;

        EstoqueDao estoqueDao = DaoFactory.createEstoqueDao();
        CarrinhoDao carrinhoDao = DaoFactory.createCarrinhoDao();
        ItensCarrinhoDao itensCarrinhoDao = DaoFactory.createItensCarrinhoDao();

        System.out.println("O que você deseja gerenciar?");
        System.out.println("1 - Estoque");
        System.out.println("2 - Carrinho de compras");

        int entradaGerenciador = sc.nextInt(); // fazer loop até inserir uma entrada válida

            if (entradaGerenciador == 1) {

                while(entradaEstoque != 0) {
                    System.out.println("1 - Adicionar produto ao estoque");
                    System.out.println("2 - Atualizar produto do estoque");
                    System.out.println("3 - Remover produto do estoque");
                    System.out.println("4 - Procurar produto do estoque por id");
                    System.out.println("5 - Procurar todos os produtos do estoque");
                    System.out.println("0 - Sair");

                    entradaEstoque = sc.nextInt();
                    sc.nextLine();

                    if (entradaEstoque == 1) {
                        try {
                            Estoque estoque = new Estoque();

                            System.out.print("Nome do produto: ");
                            String nome = sc.nextLine();
                            estoque.validarNome(nome);


                            System.out.print("Categoria (CAMISETA/CALCA/CALCADO): ");
                            String categoria = sc.nextLine().toUpperCase();
                            estoque.validarCategoria(categoria);


                            System.out.print("Valor: ");
                            Double valor = sc.nextDouble();
                            estoque.validarValor(valor);

                            System.out.print("Quantidade: ");
                            int quantidade = sc.nextInt();
                            estoque.validarQuantidade(quantidade);


                            estoque = new Estoque(null, nome, Categoria.valueOf(categoria), valor, quantidade);
                            estoqueDao.inserir(estoque);
                            System.out.println("Produto inserido no estoque com sucesso!");

                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro ao adicionar o produto: " + e.getMessage());
                            System.out.println("Tente novamente");
                        }
                    }

                    if (entradaEstoque == 2) {
                        try {
                            char escolha = 'n';
                            List<Estoque> listaEstoque = estoqueDao.encontrarTodos();
                            for(Estoque e : listaEstoque){
                                System.out.println(e);
                            }

                            System.out.print("Qual o id do produto que deseja alterar? ");
                            int id = sc.nextInt();

                            Estoque estoque = estoqueDao.encontrarPorId(id);

                            do {
                                System.out.print("Nome atual do produto = " + estoque.getNome() + ". Deseja alterá-lo? (s/n) ");
                                escolha = sc.next().toLowerCase().charAt(0);
                            } while (escolha != 's' && escolha != 'n');

                            if (escolha == 's') {
                                System.out.print("Novo nome do produto: ");
                                sc.nextLine();
                                String nome = sc.nextLine();
                                estoque.validarNome(nome);
                                estoque.setNome(nome);
                            }

                            do {
                                System.out.print("Nome atual da categoria = " + estoque.getCategoria() + ". Deseja alterá-lo?(s/n) ");
                                escolha = sc.next().toLowerCase().charAt(0);
                            } while (escolha != 's' && escolha != 'n');

                            if (escolha == 's') {
                                System.out.print("Novo nome da categoria: ");
                                String categoria = sc.next();
                                estoque.validarCategoria(categoria);
                                estoque.setCategoria(Categoria.valueOf(categoria));
                            }

                            do {
                                System.out.print("Valor atual do produto = " + estoque.getValor() + ". Deseja alterá-lo?(s/n) ");
                                escolha = sc.next().toLowerCase().charAt(0);
                            } while (escolha != 's' && escolha != 'n');

                            if (escolha == 's') {
                                System.out.print("Novo valor do produto: ");
                                Double valor = sc.nextDouble();
                                estoque.validarValor(valor);
                                estoque.setValor(valor);
                            }

                            do {
                                System.out.print("Quantidade atual do produto = " + estoque.getQuantidade() + ". Deseja alterá-lo?(s/n) ");
                                escolha = sc.next().toLowerCase().charAt(0);
                            } while (escolha != 's' && escolha != 'n');

                            if (escolha == 's') {
                                System.out.print("Nova quantidade do produto: ");
                                int quantidade = sc.nextInt();
                                estoque.validarQuantidade(quantidade);
                                estoque.setQuantidade(quantidade);
                            }

                            estoqueDao.atualizar(estoque);
                            System.out.println("Produto atualizado com sucesso!");

                        } catch (IllegalArgumentException e){
                            System.out.println("Erro ao atualizar o produto: " + e.getMessage());
                            System.out.println("Tente novamente");
                        }
                    }

                    if (entradaEstoque == 3) {
                        List<Estoque> listaEstoque = estoqueDao.encontrarTodos();
                        for(Estoque e : listaEstoque){
                            System.out.println(e);
                        }

                        System.out.print("Qual o id do produto que deseja remover? ");
                        int id = sc.nextInt();

                        estoqueDao.deletarPorId(id);
                        System.out.println("Produto removido com sucesso!"); //quando o produto for removido, fazer algo para tirar do itens carrinho ou equivalente para não dar problema
                    }

                    if (entradaEstoque == 4) {
                        try {
                            System.out.print("Qual o id do produto que você está procurando? ");
                            int id = sc.nextInt();

                            Estoque estoque = estoqueDao.encontrarPorId(id);

                            System.out.println(estoque);

                        } catch (IllegalArgumentException e){
                            System.out.println("Erro ao procurar o produto: " + e.getMessage());
                            System.out.println("Tente novamente");
                        }
                    }

                    if (entradaEstoque == 5) {
                        List<Estoque> listaEstoque = estoqueDao.encontrarTodos();

                        for (Estoque estoque : listaEstoque) {
                            System.out.println(estoque);
                        }
                    }
                }
            }

            if(entradaGerenciador == 2){
                Carrinho carrinho = new Carrinho(null);
                //carrinhoDao.inserir(carrinho);

                while(entradaCarrinho != 0) {
                    System.out.println("1 - Adicionar item ao carrinho");
                    System.out.println("2 - Atualizar item do carrinho");
                    System.out.println("3 - Remover item do carrinho");
                    System.out.println("4 - Remover carrinho");
                    System.out.println("5 - Procurar item do carrinho por id");
                    System.out.println("6 - Procurar todos os itens do carrinho");
                    System.out.println("7 - Procurar carrinho");
                    System.out.println("8 - Procurar todos os carrinhos");
                    System.out.println("0 - Sair");

                    entradaCarrinho = sc.nextInt();
                    sc.nextLine();

                    if (entradaCarrinho == 1) {
                        List<Estoque> listaEstoque = estoqueDao.encontrarTodos();
                        for (Estoque estoque : listaEstoque) {
                            System.out.println(estoque);
                        }

                        System.out.print("ID do produto: ");
                        int idProduto = sc.nextInt();

                        System.out.print("Quantidade: ");
                        int quantidade = sc.nextInt();

                        Estoque estoque = estoqueDao.encontrarPorId(idProduto);
                        ItensCarrinho itensCarrinho = new ItensCarrinho(null, estoque, quantidade, carrinho.getId());
                        carrinho.adicionarItem(itensCarrinho);
                        carrinhoDao.atualizar(carrinho);
                        itensCarrinhoDao.inserir(itensCarrinho);
                    }

                    if(entradaCarrinho == 2) {
                        List<ItensCarrinho> listaItens = new ArrayList<>();
                        System.out.print("Deseja atualizar produtos do carrinho atual?(s/n) ");
                        char escolha = sc.next().charAt(0);

                        if(escolha == 's') {//verificar se existe produtos nesse carrinho
                            listaItens = itensCarrinhoDao.encontrarPorCarrinho(carrinho);
                        }
                        if(escolha == 'n'){//verificar se existe produtos nesse carrinho
                            List<Carrinho> carrinhos = carrinhoDao.encontrarTodos();
                            for (Carrinho listaCarrinho : carrinhos) {
                                System.out.println(listaCarrinho);
                            }

                            System.out.println("Qual o id do carrinho que deseja atualizar os itens? ");
                            int idCarrinho = sc.nextInt();

                            carrinho = carrinhoDao.encontrarPorId(idCarrinho);
                            listaItens = itensCarrinhoDao.encontrarPorCarrinho(carrinho);
                        }
                            for (ItensCarrinho item : listaItens) {
                                System.out.println(item);
                            }

                            System.out.print("ID do item que deseja atualizar: ");
                            int id = sc.nextInt();

                            ItensCarrinho itensCarrinho = itensCarrinhoDao.encontrarPorId(id);

                            //PENSAR SE DEVO DEIXAR MOVER DE UM CARRINHO PARA OUTRO OU NÃO
//                            System.out.print("ID atual do carrinho = " + carrinho.getId() + ". Deseja alterá-lo?(s/n) ");
//                            escolha = sc.next().charAt(0); // fazer loop até ser escolhido s/n
//                            if (escolha == 's') {
//                                System.out.print("Novo id do carrinho: ");
//                                int idCarrinho = sc.nextInt();
//                                itensCarrinho.setCarrinho_id(idCarrinho);
//                            }

                            System.out.print("Produto atual do carrinho = " + itensCarrinho.getProduto() + ". Deseja alterá-lo?(s/n) ");
                            escolha = sc.next().charAt(0); // fazer loop até ser escolhido s/n
                            if (escolha == 's') {
                                List<Estoque> listaEstoque = estoqueDao.encontrarTodos();
                                for (Estoque estoque : listaEstoque) {
                                    System.out.println(estoque);
                                }
                                System.out.println();
                                System.out.print("Novo id do produto: ");
                                int idProduto = sc.nextInt();
                                Estoque estoque = estoqueDao.encontrarPorId(idProduto);
                                itensCarrinho.setProduto(estoque);
                            }

                            System.out.print("Quantidade atual do produto no carrinho = " + itensCarrinho.getQuantidade() + ". Deseja alterá-lo?(s/n) ");
                            escolha = sc.next().charAt(0); // fazer loop até ser escolhido s/n
                            if (escolha == 's') {
                                System.out.print("Nova quantidade de itens: ");
                                int quantidade = sc.nextInt();
                                itensCarrinho.setQuantidade(quantidade);
                            }

                            itensCarrinhoDao.atualizar(itensCarrinho);
                            List<ItensCarrinho> listaValores = itensCarrinhoDao.encontrarPorCarrinho(carrinho);
                            carrinho.atualizarValorBanco(listaValores);
                            carrinhoDao.atualizar(carrinho);

                        System.out.println("Produto atualizado com sucesso!");
                    }

                    if(entradaCarrinho == 3) {
                        List<ItensCarrinho> listaItens = new ArrayList<>();
                        System.out.print("Deseja remover um item do carrinho atual?(s/n) ");
                        char escolha = sc.next().charAt(0);

                        if(escolha == 's') {//verificar se existe produtos nesse carrinho
                            listaItens = itensCarrinhoDao.encontrarPorCarrinho(carrinho);
                        }
                        if(escolha == 'n'){//verificar se existe produtos nesse carrinho
                            List<Carrinho> carrinhos = carrinhoDao.encontrarTodos();
                            for (Carrinho listaCarrinho : carrinhos) {
                                System.out.println(listaCarrinho);
                            }
                            System.out.println("Qual o id do carrinho que deseja remover o item? ");
                            int idCarrinho = sc.nextInt();

                            carrinho = carrinhoDao.encontrarPorId(idCarrinho);
                            listaItens = itensCarrinhoDao.encontrarPorCarrinho(carrinho);
                        }

                        for (ItensCarrinho item : listaItens) {
                            System.out.println(item);
                        }

                        System.out.print("ID do item que deseja remover: ");
                        int id = sc.nextInt();

                        itensCarrinhoDao.deletarPorId(id);
                        List<ItensCarrinho> listaValores = itensCarrinhoDao.encontrarPorCarrinho(carrinho);
                        carrinho.atualizarValorBanco(listaValores);
                        carrinhoDao.atualizar(carrinho);

                        System.out.println("Produto removido com sucesso!");
                    }

                    if(entradaCarrinho == 4) {
                        List<Carrinho> listaCarrinhos = carrinhoDao.encontrarTodos();
                        for (Carrinho carrinhos: listaCarrinhos) {
                            System.out.println(carrinhos);
                        }

                        System.out.print("Qual o id do carrinho que deseja remover? ");
                        int id = sc.nextInt();

                        carrinhoDao.deletarPorId(id);
                        System.out.println("Carrinho deletado com sucesso!");
                    }

                    if(entradaCarrinho == 5) {
                        List<Carrinho> listaCarrinhos = carrinhoDao.encontrarTodos();
                        for (Carrinho carrinhos: listaCarrinhos) {
                            System.out.println(carrinhos);
                        }

                        System.out.print("Qual o id do carrinho que você quer procurar o item? ");
                        int id = sc.nextInt();

                        carrinho = carrinhoDao.encontrarPorId(id);

                        System.out.print("Qual o id do produto que você quer encontrar? ");
                        int idItem = sc.nextInt();

                        List<ItensCarrinho> itens = itensCarrinhoDao.encontrarPorCarrinho(carrinho);

                        ItensCarrinho itemEspecifico = itens.stream().filter(item -> item.getId().equals(idItem)).findFirst().orElse(null);

                        System.out.println(itemEspecifico); //fazer validações para caso nao encontrar produto pelo id

                    }

                    if(entradaCarrinho == 6) {
                        List<Carrinho> listaCarrinhos = carrinhoDao.encontrarTodos();
                        for (Carrinho carrinhos: listaCarrinhos) {
                            System.out.println(carrinhos);
                        }

                        System.out.print("Qual o id do carrinho que você quer procurar os itens? ");
                        int id = sc.nextInt();

                        carrinho = carrinhoDao.encontrarPorId(id);

                        List<ItensCarrinho> itens = itensCarrinhoDao.encontrarPorCarrinho(carrinho);

                        for (ItensCarrinho item: itens) {
                            System.out.println(item);
                        }
                    }

                    if(entradaCarrinho == 7) {
                        System.out.print("Qual o id do carrinho que você está procurando? ");
                        int id = sc.nextInt();

                        Carrinho carrinhoEspecifico = carrinhoDao.encontrarPorId(id);

                        System.out.println(carrinhoEspecifico);
                    }

                    if(entradaCarrinho == 8) {
                        List<Carrinho> listaCarrinhos = carrinhoDao.encontrarTodos();

                        for (Carrinho carrinhos: listaCarrinhos) {
                            System.out.println(carrinhos);
                        }
                    }

                    //fazer lógica para sair do while
                }
            }

        }
        
    }
