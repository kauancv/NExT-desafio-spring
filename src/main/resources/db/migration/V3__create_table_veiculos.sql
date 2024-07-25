CREATE TABLE veiculos (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(255) NOT NULL ,
    modelo VARCHAR(255) NOT NULL ,
    ano_fabricacao INT NOT NULL ,
    id_cliente BIGINT ,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id)
);