package com.celc.api.models;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "venda_itens")
public class VendaItem {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "venda_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_venda_itens_venda")
    )
    private Venda venda;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "produto_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_venda_itens_produto")
    )
    private Produto produto;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "preco_unitario", precision = 10, scale = 2, nullable = false)
    private BigDecimal precoUnitario;

    @Column(name = "subtotal", precision = 10, scale = 2, nullable = false)
    private BigDecimal subtotal;

}
