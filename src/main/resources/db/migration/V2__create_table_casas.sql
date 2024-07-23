CREATE TABLE casas (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    status VARCHAR(255) NOT NULL ,
    endereco_completo VARCHAR(255) NOT NULL ,
    cep VARCHAR(255) NOT NULL ,
    cliente_id BIGINT NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)

);
