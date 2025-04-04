package uol.compass.carrinho;

import uol.compass.carrinho.DB.DbExcessao;
import uol.compass.carrinho.enums.Categoria;
import uol.compass.carrinho.enums.Status;
import uol.compass.carrinho.modelo.dao.CarrinhoDao;
import uol.compass.carrinho.modelo.dao.DaoFabrica;
import uol.compass.carrinho.modelo.dao.EstoqueDao;
import uol.compass.carrinho.modelo.dao.ItensCarrinhoDao;
import uol.compass.carrinho.modelo.entidades.Carrinho;
import uol.compass.carrinho.modelo.entidades.Estoque;
import uol.compass.carrinho.modelo.entidades.ItensCarrinho;
import uol.compass.carrinho.utilidade.CarrinhoUtil;
import uol.compass.carrinho.utilidade.EstoqueUtil;

import java.util.*;


public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        int entradaGerenciador = 1;

        EstoqueDao estoqueDao = DaoFabrica.criarEstoqueDao();
        CarrinhoDao carrinhoDao = DaoFabrica.criarCarrinhoDao();
        ItensCarrinhoDao itensCarrinhoDao = DaoFabrica.criarItensCarrinhoDao();

        while (entradaGerenciador != 0) {
            try {
                int entradaEstoque = 1;
                int entradaCarrinho = 1;

                System.out.println("O que você deseja gerenciar?");
                System.out.println("1 - Estoque");
                System.out.println("2 - Carrinho de compras");
                System.out.println("0 - Sair");

                try {
                    entradaGerenciador = sc.nextInt();
                } catch (InputMismatchException e) {
                    sc.nextLine();
                    throw new InputMismatchException("O valor deve ser um número inteiro.");
                }

                if (entradaGerenciador == 1) {

                    while (entradaEstoque != 0) {
                        try {
                            System.out.println("1 - Adicionar produto ao estoque");
                            System.out.println("2 - Atualizar produto do estoque");
                            System.out.println("3 - Remover produto do estoque");
                            System.out.println("4 - Procurar produto do estoque por id");
                            System.out.println("5 - Procurar todos os produtos do estoque");
                            System.out.println("0 - Sair");

                            try {
                                entradaEstoque = sc.nextInt();
                            } catch (InputMismatchException e) {
                                sc.nextLine();
                                throw new InputMismatchException("O valor deve ser um número inteiro.");
                            }

                            sc.nextLine();

                            if (entradaEstoque == 1) {
                                try {
                                    Estoque estoque = new Estoque();
                                    Double valor;
                                    int quantidade;

                                    System.out.print("Nome do produto: ");
                                    String nome = sc.nextLine();
                                    estoque.validarNome(nome);

                                    System.out.print("Categoria (CAMISETA/CALCA/CALCADO): ");
                                    String categoria = sc.nextLine().toUpperCase();
                                    estoque.validarCategoria(categoria);

                                    System.out.print("Valor: ");
                                    try {
                                        valor = sc.nextDouble();
                                        estoque.validarValor(valor);
                                    } catch (InputMismatchException e) {
                                        sc.nextLine();
                                        throw new InputMismatchException("O valor deve ser um número decimal.");
                                    }

                                    System.out.print("Quantidade: ");
                                    try {
                                        quantidade = sc.nextInt();
                                        estoque.validarQuantidade(quantidade);
                                    } catch (InputMismatchException e) {
                                        sc.nextLine();
                                        throw new InputMismatchException("O valor deve ser um número inteiro.");
                                    }

                                    System.out.print("Status (DISPONIVEL/INDISPONIVEL): ");
                                    sc.nextLine();
                                    String status_produto = sc.nextLine().toUpperCase();
                                    estoque.validarStatus(status_produto);

                                    estoque = new Estoque(null, nome, Categoria.valueOf(categoria), valor, quantidade, Status.valueOf(status_produto));
                                    estoqueDao.inserir(estoque);
                                    System.out.println("Produto inserido no estoque com sucesso!");
                                    System.out.println();

                                } catch (IllegalArgumentException | InputMismatchException e) {
                                    System.out.println("Erro ao adicionar o produto: " + e.getMessage());
                                    System.out.println();
                                }
                            }

                            if (entradaEstoque == 2) {
                                try {
                                    char escolha;
                                    int id;
                                    List<Estoque> listaEstoque = estoqueDao.encontrarTodos();
                                    for (Estoque e : listaEstoque) {
                                        System.out.println(e);
                                    }
                                    System.out.println();

                                    System.out.print("Qual o id do produto que deseja alterar? ");
                                    try {
                                        id = sc.nextInt();
                                    } catch (InputMismatchException e) {
                                        sc.nextLine();
                                        throw new InputMismatchException("O valor deve ser um número inteiro.");
                                    }

                                    Estoque estoque = estoqueDao.encontrarPorId(id);

                                    do {
                                        System.out.print("Nome atual do produto = " + estoque.getNome() + ". Deseja alterá-lo?(s/n) ");
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
                                        try {
                                            Double valor = sc.nextDouble();
                                            estoque.validarValor(valor);
                                            estoque.setValor(valor);
                                        } catch (InputMismatchException e) {
                                            sc.nextLine();
                                            throw new InputMismatchException("O valor deve ser um número decimal.");
                                        }
                                    }

                                    do {
                                        System.out.print("Quantidade atual do produto = " + estoque.getQuantidade() + ". Deseja alterá-lo?(s/n) ");
                                        escolha = sc.next().toLowerCase().charAt(0);
                                    } while (escolha != 's' && escolha != 'n');

                                    if (escolha == 's') {
                                        System.out.print("Nova quantidade do produto: ");
                                        try {
                                            int quantidade = sc.nextInt();
                                            estoque.validarQuantidade(quantidade);
                                            estoque.setQuantidade(quantidade);
                                        } catch (InputMismatchException e) {
                                            sc.nextLine();
                                            throw new InputMismatchException("O valor deve ser um número inteiro.");
                                        }
                                    }

                                    do {
                                        System.out.print("Status atual do produto = " + estoque.getStatus_produto() + ". Deseja alterá-lo?(s/n) ");
                                        escolha = sc.next().toLowerCase().charAt(0);
                                    } while (escolha != 's' && escolha != 'n');

                                    if (escolha == 's') {
                                        System.out.print("Novo status do produto: ");
                                        String status_produto = sc.next().toUpperCase();
                                        estoque.validarStatus(status_produto);
                                        estoque.setStatus_produto(Status.valueOf(status_produto));
                                    }

                                    estoqueDao.atualizar(estoque);

                                    List<Carrinho> listaCarrinhos = carrinhoDao.encontrarTodos();

                                    //Se fosse pensar em uma base de dados grande, fica inviável atualizar todos os carrinhos de compra
                                    for (Carrinho c : listaCarrinhos) {
                                        List<ItensCarrinho> itensCarrinho = itensCarrinhoDao.encontrarPorCarrinho(c);
                                        c.atualizarValorBanco(itensCarrinho);
                                        carrinhoDao.atualizar(c);
                                    }

                                    System.out.println("Produto atualizado com sucesso!");
                                    System.out.println();

                                } catch (IllegalArgumentException | InputMismatchException e) {
                                    System.out.println("Erro ao atualizar o produto: " + e.getMessage());
                                    System.out.println();
                                }
                            }

                            if (entradaEstoque == 3) {
                                try {
                                    int escolhaRemover;
                                    int id;
                                    System.out.println("1 - Remover o produto");
                                    System.out.println("2 - Tornar o produto indisponível");
                                    System.out.println("AVISO: remover o produto também irá excluí-lo de todos os carrinhos em que o item está inserido.");

                                    try {
                                        escolhaRemover = sc.nextInt();
                                    } catch (InputMismatchException e) {
                                        sc.nextLine();
                                        throw new InputMismatchException("O valor deve ser um número inteiro.");
                                    }

                                    List<Estoque> listaEstoque = estoqueDao.encontrarTodos();
                                    for (Estoque e : listaEstoque) {
                                        System.out.println(e);
                                    }

                                    System.out.println();

                                    if (escolhaRemover == 1) {
                                        System.out.print("Qual o id do produto que deseja remover? ");

                                        try {
                                            id = sc.nextInt();
                                        } catch (InputMismatchException e) {
                                            sc.nextLine();
                                            throw new InputMismatchException("O valor deve ser um número inteiro.");
                                        }

                                        estoqueDao.deletarPorId(id);

                                        List<Carrinho> listaCarrinhos = carrinhoDao.encontrarTodos();

                                        //Se fosse pensar em uma base de dados grande, fica inviável atualizar todos os carrinhos de compra
                                        for (Carrinho c : listaCarrinhos) {
                                            List<ItensCarrinho> itensCarrinho = itensCarrinhoDao.encontrarPorCarrinho(c);
                                            c.atualizarValorBanco(itensCarrinho);
                                            carrinhoDao.atualizar(c);
                                        }

                                        System.out.println("Produto removido com sucesso!");
                                        System.out.println();
                                    }

                                    if (escolhaRemover == 2) {
                                        System.out.print("Qual o id do produto que deseja tornar indisponível? ");

                                        try {
                                            id = sc.nextInt();
                                        } catch (InputMismatchException e) {
                                            sc.nextLine();
                                            throw new InputMismatchException("O valor deve ser um número inteiro.");
                                        }

                                        Estoque estoque = estoqueDao.encontrarPorId(id);
                                        estoque.setStatus_produto(Status.INDISPONIVEL);
                                        estoqueDao.atualizar(estoque);

                                        System.out.println("Produto tornou-se indisponível!");
                                        System.out.println();
                                    }


                                } catch (IllegalArgumentException | InputMismatchException e) {
                                    System.out.println("Erro ao remover o produto: " + e.getMessage());
                                    System.out.println();
                                }
                            }

                            if (entradaEstoque == 4) {
                                try {
                                    System.out.print("Qual o id do produto que você está procurando? ");
                                    int id;

                                    try {
                                        id = sc.nextInt();
                                    } catch (InputMismatchException e) {
                                        sc.nextLine();
                                        throw new InputMismatchException("O valor deve ser um número inteiro.");
                                    }

                                    Estoque estoque = estoqueDao.encontrarPorId(id);


                                    System.out.println(estoque);
                                    System.out.println();

                                } catch (IllegalArgumentException | InputMismatchException e) {
                                    System.out.println("Erro ao procurar o produto: " + e.getMessage());
                                    System.out.println();
                                }
                            }

                            if (entradaEstoque == 5) {
                                try {
                                    List<Estoque> listaEstoque = estoqueDao.encontrarTodos();

                                    for (Estoque estoque : listaEstoque) {
                                        System.out.println(estoque);
                                    }

                                    System.out.println();
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Erro ao procurar todos os produtos: " + e.getMessage());
                                    System.out.println();
                                }
                            }

                        } catch (InputMismatchException e){
                            System.out.println("Erro ao selecionar opção: " + e.getMessage());
                            System.out.println();
                        }
                    }
                }

                if (entradaGerenciador == 2) {

                    List<Carrinho> listaExcluirCarrinhos = carrinhoDao.encontrarTodos();

                    for (Carrinho carrinho : listaExcluirCarrinhos) {
                        if (carrinho.getValor_total() == 0) {
                            carrinhoDao.deletarPorId(carrinho.getId());
                        }
                    }

                    Carrinho carrinho = new Carrinho(null);
                    carrinhoDao.inserir(carrinho);

                    while (entradaCarrinho != 0) {
                        try {
                            System.out.println("1 - Adicionar item ao carrinho");
                            System.out.println("2 - Atualizar item do carrinho");
                            System.out.println("3 - Remover item do carrinho");
                            System.out.println("4 - Remover carrinho");
                            System.out.println("5 - Procurar item do carrinho por id");
                            System.out.println("6 - Procurar todos os itens do carrinho");
                            System.out.println("7 - Procurar carrinho");
                            System.out.println("8 - Procurar todos os carrinhos");
                            System.out.println("0 - Sair");

                            try {
                                entradaCarrinho = sc.nextInt();
                            } catch (InputMismatchException e) {
                                sc.nextLine();
                                throw new InputMismatchException("O valor deve ser um número inteiro.");
                            }

                            sc.nextLine();

                            if (entradaCarrinho == 1) {
                                try {
                                    int idCarrinho;
                                    int idProduto;
                                    int quantidade;
                                    char escolha;

                                    do {
                                        System.out.print("Deseja inserir produtos do carrinho atual #" + carrinho.getId() + "?(s/n) ");
                                        escolha = sc.next().toLowerCase().charAt(0);
                                    } while (escolha != 's' && escolha != 'n');

                                    if(escolha == 's'){
                                        carrinho = carrinhoDao.encontrarPorId(carrinho.getId()); //verificando se o ID ainda existe
                                    }

                                    if (escolha == 'n') {
                                        List<Carrinho> carrinhos = carrinhoDao.encontrarTodos();
                                        for (Carrinho listaCarrinho : carrinhos) {
                                            System.out.println(listaCarrinho);
                                        }

                                        System.out.println();

                                        System.out.print("Qual o id do carrinho que deseja inserir os itens? ");

                                        try {
                                            idCarrinho = sc.nextInt();
                                        } catch (InputMismatchException e) {
                                            sc.nextLine();
                                            throw new InputMismatchException("O valor deve ser um número inteiro.");
                                        }

                                        carrinho = carrinhoDao.encontrarPorId(idCarrinho);
                                    }

                                    ItensCarrinho itensCarrinho = new ItensCarrinho();

                                    EstoqueUtil.exibirListaEstoque(estoqueDao);

                                    System.out.print("ID do produto: ");

                                    try {
                                        idProduto = sc.nextInt();
                                    } catch (InputMismatchException e) {
                                        sc.nextLine();
                                        throw new InputMismatchException("O valor deve ser um número inteiro.");
                                    }

                                    System.out.print("Quantidade: ");

                                    try {
                                        quantidade = sc.nextInt();
                                    } catch (InputMismatchException e) {
                                        sc.nextLine();
                                        throw new InputMismatchException("O valor deve ser um número inteiro.");
                                    }

                                    itensCarrinho.validarQuantidade(quantidade);

                                    Estoque estoque = estoqueDao.encontrarPorId(idProduto);
                                    estoque.verficarDisponibilidade(estoque);

                                    itensCarrinho = new ItensCarrinho(null, estoque, quantidade, carrinho.getId());

                                    estoque.atualizarQuantidade(itensCarrinho);
                                    carrinho.adicionarItem(itensCarrinho);
                                    carrinhoDao.atualizar(carrinho);
                                    itensCarrinhoDao.inserir(itensCarrinho);

                                    List<ItensCarrinho> listaValores = itensCarrinhoDao.encontrarPorCarrinho(carrinho);
                                    carrinho.atualizarValorBanco(listaValores);
                                    carrinhoDao.atualizar(carrinho);

                                    estoqueDao.atualizar(estoque);

                                    System.out.println("Produto inserido com sucesso!");
                                    System.out.println();

                                } catch (IllegalArgumentException | InputMismatchException e) {
                                    System.out.println("Erro ao inserir produto no carrinho: " + e.getMessage());
                                    System.out.println();
                                }
                            }

                            if (entradaCarrinho == 2) {
                                try {
                                    int idCarrinho;
                                    int id;
                                    int idProduto;
                                    int quantidade;
                                    boolean verdadeiro = true;

                                    List<ItensCarrinho> listaItens = new ArrayList<>();
                                    char escolha;

                                    do {
                                        System.out.print("Deseja atualizar produtos do carrinho atual #" + carrinho.getId() + "?(s/n) ");
                                        escolha = sc.next().toLowerCase().charAt(0);
                                    } while (escolha != 's' && escolha != 'n');

                                    if (escolha == 's') {
                                        carrinho = carrinhoDao.encontrarPorId(carrinho.getId()); //verificando se o ID ainda existe
                                        listaItens = itensCarrinhoDao.encontrarPorCarrinho(carrinho);

                                        if (listaItens.isEmpty()) {
                                            throw new IllegalStateException("O carrinho não possui itens");
                                        }
                                    }
                                    if (escolha == 'n') {
                                        List<Carrinho> carrinhos = carrinhoDao.encontrarTodos();
                                        for (Carrinho listaCarrinho : carrinhos) {
                                            System.out.println(listaCarrinho);
                                        }

                                        System.out.println();

                                        System.out.println("Qual o id do carrinho que deseja atualizar os itens? ");

                                        try {
                                            idCarrinho = sc.nextInt();
                                        } catch (InputMismatchException e) {
                                            sc.nextLine();
                                            throw new InputMismatchException("O valor deve ser um número inteiro.");
                                        }

                                        carrinho = carrinhoDao.encontrarPorId(idCarrinho);
                                        listaItens = itensCarrinhoDao.encontrarPorCarrinho(carrinho);

                                        if (listaItens.isEmpty()) {
                                            throw new IllegalStateException("O carrinho não possui itens");
                                        }
                                    }

                                    for (ItensCarrinho item : listaItens) {
                                        System.out.println(item);
                                    }

                                    System.out.println();

                                    System.out.print("ID do item que deseja atualizar: ");

                                    try {
                                        id = sc.nextInt();
                                    } catch (InputMismatchException e) {
                                        sc.nextLine();
                                        throw new InputMismatchException("O valor deve ser um número inteiro.");
                                    }

                                    ItensCarrinho itensCarrinho = itensCarrinhoDao.encontrarPorId(id);

                                    if(!listaItens.contains(itensCarrinho)){
                                            throw new IllegalStateException("O carrinho não possui esse produto.");
                                    }

                                    Estoque produtoAntigo = itensCarrinho.getProduto();
                                    int quantidadeAntiga = itensCarrinho.getQuantidade();

                                    do {
                                        System.out.print("Produto atual do carrinho = " + itensCarrinho.getProduto() + ". Deseja alterá-lo?(s/n) ");
                                        escolha = sc.next().charAt(0);
                                    } while (escolha != 's' && escolha != 'n');

                                    if (escolha == 's') {
                                        List<Estoque> listaEstoque = estoqueDao.encontrarTodos();

                                        for (Estoque estoque : listaEstoque) {
                                            if(!estoque.equals(itensCarrinho.getProduto())){
                                                System.out.println(estoque);
                                            }
                                        }

                                        System.out.println();

                                        System.out.print("Novo id do produto: ");

                                        try {
                                            idProduto = sc.nextInt();
                                        } catch (InputMismatchException e) {
                                            sc.nextLine();
                                            throw new InputMismatchException("O valor deve ser um número inteiro.");
                                        }

                                        if(idProduto == itensCarrinho.getProduto().getId()){
                                            throw new IllegalArgumentException("O ID não pode ser o mesmo do produto que você deseja atualizar.");
                                        }

                                        Estoque estoque = estoqueDao.encontrarPorId(idProduto);
                                        estoque.verficarDisponibilidade(estoque);
                                        itensCarrinho.setProduto(estoque);
                                    }

                                    if (escolha == 'n') {
                                        verdadeiro = false;
                                    }

                                    produtoAntigo.setQuantidade(produtoAntigo.getQuantidade() + quantidadeAntiga);

                                    do {
                                        System.out.print("Quantidade atual do produto no carrinho = " + itensCarrinho.getQuantidade() + ". Deseja alterá-lo?(s/n) ");
                                        escolha = sc.next().toLowerCase().charAt(0);
                                    } while (escolha != 's' && escolha != 'n');

                                    if (escolha == 's') {
                                        System.out.print("Nova quantidade de itens: ");

                                        try {
                                            quantidade = sc.nextInt();
                                        } catch (InputMismatchException e) {
                                            sc.nextLine();
                                            throw new InputMismatchException("O valor deve ser um número inteiro.");
                                        }

                                        itensCarrinho.validarQuantidade(quantidade);
                                        itensCarrinho.setQuantidade(quantidade);
                                    }

                                    if (verdadeiro) {
                                        Estoque estoque = estoqueDao.encontrarPorId(itensCarrinho.getProduto().getId());
                                        estoque.atualizarQuantidade(itensCarrinho);
                                        estoqueDao.atualizar(produtoAntigo);
                                        itensCarrinhoDao.atualizar(itensCarrinho);
                                        estoqueDao.atualizar(estoque);
                                        List<ItensCarrinho> listaValores = itensCarrinhoDao.encontrarPorCarrinho(carrinho);
                                        carrinho.atualizarValorBanco(listaValores);
                                        carrinhoDao.atualizar(carrinho);
                                        System.out.println("Produto atualizado com sucesso!");
                                        System.out.println();
                                    } else {
                                        produtoAntigo.atualizarQuantidade(itensCarrinho);
                                        estoqueDao.atualizar(produtoAntigo);
                                        itensCarrinhoDao.atualizar(itensCarrinho);
                                        List<ItensCarrinho> listaValores = itensCarrinhoDao.encontrarPorCarrinho(carrinho);
                                        carrinho.atualizarValorBanco(listaValores);
                                        carrinhoDao.atualizar(carrinho);
                                        System.out.println("Produto atualizado com sucesso!");
                                        System.out.println();
                                    }

                                } catch (IllegalArgumentException | IllegalStateException | InputMismatchException e) {
                                    System.out.println("Erro ao atualizar item do carrinho: " + e.getMessage());
                                    System.out.println();
                                }
                            }

                            if (entradaCarrinho == 3) {
                                try {
                                    List<ItensCarrinho> listaItens = new ArrayList<>();
                                    char escolha;
                                    int idCarrinho;
                                    int id;

                                    do {
                                        System.out.print("Deseja remover um item do carrinho atual #" + carrinho.getId() + "?(s/n) ");
                                        escolha = sc.next().toLowerCase().charAt(0);
                                    } while (escolha != 's' && escolha != 'n');

                                    if (escolha == 's') {
                                        carrinho = carrinhoDao.encontrarPorId(carrinho.getId()); //verificando se o ID ainda existe
                                        listaItens = itensCarrinhoDao.encontrarPorCarrinho(carrinho);

                                        if (listaItens.isEmpty()) {
                                            throw new IllegalStateException("O carrinho não possui itens");
                                        }
                                    }
                                    if (escolha == 'n') {
                                        CarrinhoUtil.exibirListaCarrinhos(carrinhoDao);

                                        System.out.println("Qual o id do carrinho que deseja remover o item? ");

                                        try {
                                            idCarrinho = sc.nextInt();
                                        } catch (InputMismatchException e) {
                                            sc.nextLine();
                                            throw new InputMismatchException("O valor deve ser um número inteiro.");
                                        }


                                        carrinho = carrinhoDao.encontrarPorId(idCarrinho);
                                        listaItens = itensCarrinhoDao.encontrarPorCarrinho(carrinho);


                                        if (listaItens.isEmpty()) {
                                            throw new IllegalStateException("O carrinho não possui itens");
                                        }
                                    }

                                    for (ItensCarrinho item : listaItens) {
                                        System.out.println(item);
                                    }

                                    System.out.println();

                                    System.out.print("ID do item que deseja remover: ");

                                    try {
                                        id = sc.nextInt();
                                    } catch (InputMismatchException e) {
                                        sc.nextLine();
                                        throw new InputMismatchException("O valor deve ser um número inteiro.");
                                    }

                                    ItensCarrinho ic = itensCarrinhoDao.encontrarPorId(id);

                                    if(!listaItens.contains(ic)){
                                        throw new IllegalStateException("O carrinho não possui esse produto.");
                                    }

                                    itensCarrinhoDao.deletarPorId(id);
                                    List<ItensCarrinho> listaValores = itensCarrinhoDao.encontrarPorCarrinho(carrinho);
                                    carrinho.atualizarValorBanco(listaValores);
                                    carrinhoDao.atualizar(carrinho);

                                    System.out.println("Produto removido com sucesso!");
                                    System.out.println();
                                } catch (IllegalArgumentException | IllegalStateException | InputMismatchException e) {
                                    System.out.println("Erro ao remover item do carrinho: " + e.getMessage());
                                    System.out.println();
                                }
                            }

                            if (entradaCarrinho == 4) {
                                try {
                                    int id;
                                    CarrinhoUtil.exibirListaCarrinhos(carrinhoDao);

                                    System.out.println();

                                    System.out.print("Qual o id do carrinho que deseja remover? ");

                                    try {
                                        id = sc.nextInt();
                                    } catch (InputMismatchException e) {
                                        sc.nextLine();
                                        throw new InputMismatchException("O valor deve ser um número inteiro.");
                                    }

                                    carrinhoDao.deletarPorId(id);
                                    System.out.println("Carrinho deletado com sucesso!");
                                    System.out.println();

                                } catch (IllegalArgumentException | InputMismatchException e) {
                                    System.out.println("Erro ao remover carrinho: " + e.getMessage());
                                    System.out.println();
                                }
                            }

                            if (entradaCarrinho == 5) {
                                try {
                                    int id;
                                    int idItem;
                                    CarrinhoUtil.exibirListaCarrinhos(carrinhoDao);

                                    System.out.println();

                                    System.out.print("Qual o id do carrinho que você quer procurar o item? ");

                                    try {
                                        id = sc.nextInt();
                                    } catch (InputMismatchException e) {
                                        sc.nextLine();
                                        throw new InputMismatchException("O valor deve ser um número inteiro.");
                                    }

                                    carrinho = carrinhoDao.encontrarPorId(id);

                                    System.out.print("Qual o id do produto que você quer encontrar? ");

                                    try {
                                        idItem = sc.nextInt();
                                    } catch (InputMismatchException e) {
                                        sc.nextLine();
                                        throw new InputMismatchException("O valor deve ser um número inteiro.");
                                    }

                                    List<ItensCarrinho> itens = itensCarrinhoDao.encontrarPorCarrinho(carrinho);

                                    ItensCarrinho itemEspecifico = itens.stream().filter(item -> item.getId().equals(idItem)).findFirst().orElseThrow(() -> new IllegalArgumentException("Item com esse id não foi encontrado."));

                                    System.out.println(itemEspecifico);
                                    System.out.println();
                                } catch (IllegalArgumentException | IllegalStateException | InputMismatchException e) {
                                    System.out.println("Erro ao buscar id do produto: " + e.getMessage());
                                    System.out.println();
                                }
                            }

                            if (entradaCarrinho == 6) {
                                try {
                                    int id;
                                    CarrinhoUtil.exibirListaCarrinhos(carrinhoDao);

                                    System.out.println();

                                    System.out.print("Qual o id do carrinho que você quer procurar os itens? ");

                                    try {
                                        id = sc.nextInt();
                                    } catch (InputMismatchException e) {
                                        sc.nextLine();
                                        throw new InputMismatchException("O valor deve ser um número inteiro.");
                                    }

                                    carrinho = carrinhoDao.encontrarPorId(id);

                                    List<ItensCarrinho> itens = itensCarrinhoDao.encontrarPorCarrinho(carrinho);

                                    if (itens.isEmpty()) {
                                        throw new IllegalStateException("O carrinho não possui itens");
                                    }

                                    for (ItensCarrinho item : itens) {
                                        System.out.println(item);
                                    }
                                    System.out.println();
                                } catch (IllegalArgumentException | IllegalStateException | InputMismatchException e) {
                                    System.out.println("Erro ao buscar todos os itens do carrinho: " + e.getMessage());
                                    System.out.println();
                                }
                            }

                            if (entradaCarrinho == 7) {
                                try {
                                    int id;
                                    System.out.print("Qual o id do carrinho que você está procurando? ");

                                    try {
                                        id = sc.nextInt();
                                    } catch (InputMismatchException e) {
                                        sc.nextLine();
                                        throw new InputMismatchException("O valor deve ser um número inteiro.");
                                    }

                                    Carrinho carrinhoEspecifico = carrinhoDao.encontrarPorId(id);

                                    System.out.println(carrinhoEspecifico);
                                    System.out.println();

                                } catch (IllegalArgumentException | InputMismatchException e) {
                                    System.out.println("Erro ao buscar o carrinho: " + e.getMessage());
                                    System.out.println();
                                }
                            }

                            if (entradaCarrinho == 8) {
                                try {
                                    CarrinhoUtil.exibirListaCarrinhos(carrinhoDao);

                                    System.out.println();
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Erro ao buscar todos os carrinhos: " + e.getMessage());
                                    System.out.println();
                                }
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("Erro ao selecionar opção: " + e.getMessage());
                            System.out.println();
                        }
                    }
                }

            } catch (InputMismatchException e){
                System.out.println("Erro ao selecionar opção: " + e.getMessage());
                System.out.println();
            }
        }
    }
}