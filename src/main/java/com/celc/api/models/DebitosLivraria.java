package com.celc.api.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.celc.api.models.types.StatusDebito;

import jakarta.persistence.*;

@Entity 
@Table(name = "debitos_livraria")
public class DebitosLivraria {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "venda_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_debitos_livraria_venda")
    )
    private Venda venda;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "usuario_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_debitos_livraria_usuario")
    )
    private Usuario comprador;

    @Column(name = "valor_total", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "valor_pago", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorPago;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusDebito status = StatusDebito.PENDENTE;

    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "vendedor_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_debitos_livraria_vendedor")
    )
    private Usuario vendedor;

    @Column(name = "observacao")
    private String observacao;
    
    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;
    
}
