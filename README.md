# Carrinho de compras 
Este é um projeto em Java 17 de um carrinho de compras que opera pelo terminal. Ele utiliza JDBC para conectar-se a um banco de dados MySQL, permitindo a realização de operações de inserção, atualização, deleção e buscas no banco de dados.

## Funcionalidades
* Gerenciar estoque
* Gerenciar carrinhos de compras 
* Gerenciar produtos em cada carrinho

## Tecnologias Utilizadas
* Java 17 
* JDBC
* MySQL

## Configuração

### Clonar repositório
Pelo terminal entre na pasta onde deseja que o projeto seja clonado e digite o comando a seguir.
```
git clone https://github.com/Matheus-Reato/carrinho_compras.git
```

### Criar o banco de dados no MySQL Workbench

* Entre no seu MySQL Workbench e crie um novo arquivo SQL
* Use o seguinte comando para criar um novo banco de dados
```
CREATE DATABASE carrinho_compras;
USE carrinho_compras;
```
* Selecione as linhas que acabou de escrever e as execute
* Atualize a lista de "SCHEMAS"

### Criar as tabelas do banco 
Depois de criar o banco de dados, você irá criar as tabelas do banco, pode criar um arquivo SQL novo ou usar o mesmo que criou o banco de dados.

* Copie o código abaixo e cole no arquivo SQL
```
CREATE TABLE estoque (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    categoria ENUM('CAMISETA', 'CALCA', 'CALCADO'),
    valor DECIMAL(10, 2) NOT NULL,
    quantidade INT NOT NULL,
    status_produto ENUM('DISPONIVEL', 'INDISPONIVEL')
);

CREATE TABLE carrinho (
    id INT PRIMARY KEY AUTO_INCREMENT,
    valor_total DECIMAL(10, 2) NOT NULL DEFAULT 0.0
);

CREATE TABLE itens_carrinho (
    id INT PRIMARY KEY AUTO_INCREMENT,
    carrinho_id INT NOT NULL,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL,
    FOREIGN KEY (carrinho_id) REFERENCES carrinho(id) ON DELETE CASCADE,
    FOREIGN KEY (produto_id) REFERENCES estoque(id) ON DELETE CASCADE
);
```
* Selecione essa porção de código e execute
* Atualize a lista de "SCHEMAS"

### Alterar informações de Usuário e Senha do arquivo db.propriedades

No arquivo db.propriedades que se encontra na raiz do projeto, insira o nome de usuário e a senha que você usa para acessar o MySQL no MySQL Workbench.

O arquivo db.propriedades já vem configurado com um usuário e senha padrão (root). Se você usa credenciais diferentes, basta substituir root pelo seu usuário na variável user e root pela sua senha na variável password.

### Verifique se o MySQL connector está nas bibliotecas externas do projeto

>Aviso: A configuração do projeto proposta nesse ReadME foi pensada para ser usada com a IDE IntelliJ. Se você estiver usando uma IDE diferente, será necessário consultar a documentação correspondente para aplicar as configurações adequadas.

Abra o IntelliJ IDEA.

Caso o MySQL connector não esteja presente, adicione ele ao projeto.

Se você não possui um conector MySQL instalado em sua máquina, visite https://dev.mysql.com/downloads/connector/j/

* Clique com o botão direito do mouse na pasta do projeto
* Procure a opção "Open module Settings"
* Em "Project Settings", selecione "Libraries"
* Clique no ícone de "+" e selecione "Java"
* Procure o local onde você tem instalado o seu MySQL connector
* Selecione o MySQL connector que possui a extensão .jar
* De "Ok" e "Apply"
* Feche a janela

Se a sua IDE estiver configurada em um idioma diferente, busque as opções correspondentes aos termos mencionados.

## Versões
* IntelliJ IDEA 2023.1.3
* Java 17.0.7 Zulu
* MySQL 8.0.32
* MySQL Connector J 9.1.0

Caso esteja usando versões anteriores ou posteriores e haja algum problema, considere tentar usar a mesma versão que usei.


## Como usar

* Certifique-se que fez as configurações necessárias
* Abra o IntelliJ IDEA
* Navegue até o arquivo main do projeto
* Execute o projeto

## Experiência Pessoal

Este projeto foi desenvolvido como parte do primeiro desafio do Programa de Bolsas (PB) de Spring Boot e AWS da Compass UOL. Tivemos um prazo de uma semana para entregar uma aplicação de carrinho de compras, que incluía alguns requisitos básicos, mas oferecia bastante flexibilidade para implementarmos melhorias e personalizações que considerássemos relevantes.

Uma das dificuldades que enfrentei ao longo deste projeto foi estruturar a base de dados, ao definir as tabelas necessárias e os campos de cada uma. Além de decidir como organizar adequadamente as informações em cada tabela.

Outra dificuldade que encontrei, possivelmente devido à falta de experiência, foi avaliar o progresso do projeto ao longo dos primeiros três dias. Eu sabia que estava no caminho, mas não conseguia estimar com precisão se estava adiantado, atrasado ou na média.

Do quarto para o quinto dia começei a me sentir mais confiante em relação ao meu progresso e já conseguia visualizar melhor o resultado final que eu queria alcançar.

Acredito que este projeto foi fundamental para solidificar os conteúdos que aprendi sobre Java. A prática aplicada no desenvolvimento me permitiu reforçar conceitos e ganhar confiança na linguagem e em mim mesmo.





