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