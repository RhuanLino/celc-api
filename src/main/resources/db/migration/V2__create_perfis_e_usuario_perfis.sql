CREATE TABLE perfis (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_perfis_nome UNIQUE (nome)
) ENGINE = InnoDB;

CREATE TABLE usuario_perfis (
    usuario_id BIGINT NOT NULL,
    perfil_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, perfil_id),
    CONSTRAINT fk_usuario_perfis_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id),
    CONSTRAINT fk_usuario_perfis_perfil FOREIGN KEY (perfil_id) REFERENCES perfis (id)
) ENGINE = InnoDB;
