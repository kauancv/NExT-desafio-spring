CREATE TABLE seguros (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(255) NOT NULL ,
    pontuacao_risco INT NOT NULL ,
    analise VARCHAR(255) NOT NULL ,
    observacao VARCHAR(255) NOT NULL ,
    data_criacao DATE NOT NULL ,
    data_validade DATE NOT NULL ,
    id_cliente BIGINT NOT NULL,
    FOREIGN KEY(id_cliente) REFERENCES clientes(id)

);