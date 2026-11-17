-- Baseline do schema gerenciado anteriormente por ddl-auto=update.

CREATE TABLE autores (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_autores_nome UNIQUE (nome)
) ENGINE = InnoDB;

CREATE TABLE categorias (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_categorias_nome UNIQUE (nome)
) ENGINE = InnoDB;

CREATE TABLE espiritos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_espiritos_nome UNIQUE (nome)
) ENGINE = InnoDB;

CREATE TABLE generos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_generos_nome UNIQUE (nome)
) ENGINE = InnoDB;

CREATE TABLE marcas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    ramo VARCHAR(255) DEFAULT NULL,
    cnpj BIGINT DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_marcas_nome UNIQUE (nome)
) ENGINE = InnoDB;

CREATE TABLE usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    nome_espiritual VARCHAR(255) DEFAULT NULL,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(60) DEFAULT NULL,
    quadro INT DEFAULT NULL,
    medium ENUM ('ALUNO', 'DIRIGENTE', 'INSTRUTOR', 'PASSISTA', 'PRONTO') DEFAULT NULL,
    pai VARCHAR(255) DEFAULT NULL,
    mae VARCHAR(255) DEFAULT NULL,
    telefone VARCHAR(20) DEFAULT NULL,
    data_nascimento DATE DEFAULT NULL,
    cpf VARCHAR(11) DEFAULT NULL,
    rg VARCHAR(9) DEFAULT NULL,
    estado_civil ENUM ('CASADO', 'DIVORCIADO', 'SEPARADO', 'SOLTEIRO', 'UNIAO_ESTAVEL', 'VIUVO') DEFAULT NULL,
    cidade VARCHAR(255) DEFAULT NULL,
    uf VARCHAR(2) DEFAULT NULL,
    complemento VARCHAR(255) DEFAULT NULL,
    ano_ingresso INT DEFAULT NULL,
    valor_mensalidade DECIMAL(10, 2) DEFAULT NULL,
    desligado BIT(1) NOT NULL,
    criado_em DATETIME(6) NOT NULL,
    atualizado_em DATETIME(6) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_usuarios_username UNIQUE (username),
    CONSTRAINT uk_usuarios_email UNIQUE (email),
    CONSTRAINT uk_usuarios_nome_espiritual UNIQUE (nome_espiritual),
    CONSTRAINT uk_usuarios_cpf UNIQUE (cpf)
) ENGINE = InnoDB;

CREATE TABLE produtos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    codigo_barras BIGINT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) DEFAULT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    estoque INT NOT NULL,
    validade DATE DEFAULT NULL,
    isbn BIGINT DEFAULT NULL,
    categoria_id BIGINT NOT NULL,
    marca_id BIGINT NOT NULL,
    genero_id BIGINT DEFAULT NULL,
    espirito_id BIGINT DEFAULT NULL,
    autor_id BIGINT DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_produtos_codigo_barras UNIQUE (codigo_barras),
    CONSTRAINT uk_produtos_isbn UNIQUE (isbn),
    CONSTRAINT fk_produtos_categorias FOREIGN KEY (categoria_id) REFERENCES categorias (id),
    CONSTRAINT fk_produtos_marca FOREIGN KEY (marca_id) REFERENCES marcas (id),
    CONSTRAINT fk_produtos_genero FOREIGN KEY (genero_id) REFERENCES generos (id),
    CONSTRAINT fk_produtos_espirito FOREIGN KEY (espirito_id) REFERENCES espiritos (id),
    CONSTRAINT fk_produtos_autor FOREIGN KEY (autor_id) REFERENCES autores (id)
) ENGINE = InnoDB;

CREATE TABLE vendas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    data_venda DATETIME(6) NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    forma_pagamento ENUM ('A_PRAZO', 'CREDITO', 'DEBITO', 'DINHEIRO', 'PIX') NOT NULL,
    usuario_id BIGINT DEFAULT NULL,
    observacao VARCHAR(255) DEFAULT NULL,
    criado_em DATETIME(6) NOT NULL,
    atualizado_em DATETIME(6) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_vendas_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
) ENGINE = InnoDB;

CREATE TABLE venda_itens (
    id BIGINT NOT NULL AUTO_INCREMENT,
    venda_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_venda_itens_venda FOREIGN KEY (venda_id) REFERENCES vendas (id),
    CONSTRAINT fk_venda_itens_produto FOREIGN KEY (produto_id) REFERENCES produtos (id)
) ENGINE = InnoDB;

CREATE TABLE debitos_livraria (
    id BIGINT NOT NULL AUTO_INCREMENT,
    venda_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    valor_total DECIMAL(10, 2) NOT NULL,
    valor_pago DECIMAL(10, 2) NOT NULL,
    status ENUM ('PAGO', 'PARCIAL', 'PENDENTE') NOT NULL,
    data_pagamento DATETIME(6) DEFAULT NULL,
    vendedor_id BIGINT NOT NULL,
    observacao VARCHAR(255) DEFAULT NULL,
    criado_em DATETIME(6) NOT NULL,
    atualizado_em DATETIME(6) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_debitos_livraria_venda FOREIGN KEY (venda_id) REFERENCES vendas (id),
    CONSTRAINT fk_debitos_livraria_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id),
    CONSTRAINT fk_debitos_livraria_vendedor FOREIGN KEY (vendedor_id) REFERENCES usuarios (id)
) ENGINE = InnoDB;
