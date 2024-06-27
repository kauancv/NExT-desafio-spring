CREATE TABLE clientes (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    nome VARCHAR(255) NOT NULL ,
    idade INT NOT NULL ,
    dependentes INT NOT NULL ,
    renda DECIMAL(8,2) NOT NULL ,
    estado_civil VARCHAR(8) NOT NULL ,
    data_criacao DATE NOT NULL ,
    data_atualizacao DATE
);