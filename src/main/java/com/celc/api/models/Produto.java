package com.celc.api.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(
    name = "produtos",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_produtos_codigo_barras", columnNames = "codigo_barras"),
        @UniqueConstraint(name = "uk_produtos_isbn", columnNames = "isbn")
    }
)
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_barras", nullable = false)
    private Long codigoBarras;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco", precision = 10, scale = 2, nullable = false)
    private BigDecimal preco;

    @Column(name = "estoque", nullable = false)
    private Integer estoque;

    @Column(name = "validade")
    private LocalDate validade;

    @Column(name = "isbn")
    private Long isbn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "categoria_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_produtos_categorias")
    )
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "marca_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_produtos_marca")
    )
    private Marca marca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "genero_id",
        foreignKey = @ForeignKey(name = "fk_produtos_genero")
    )
    private Genero genero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "espirito_id",
        foreignKey = @ForeignKey(name = "fk_produtos_espirito")
    )
    private Espirito espirito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "autor_id",
        foreignKey = @ForeignKey(name = "fk_produtos_autor")
    )
    private Autor autor;

}
