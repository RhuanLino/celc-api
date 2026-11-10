package com.celc.api.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.celc.api.models.types.FormaPagamento;

import jakarta.persistence.*;

@Entity
@Table(name = "vendas")
public class Venda {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @PrePersist
    void prePersist() {
        if (dataVenda == null) {
            dataVenda = LocalDateTime.now();
        }
    }
    @Column(name = "data_venda", nullable = false)
    private LocalDateTime dataVenda;

    @Column(name = "total", precision = 10, scale = 2, nullable = false)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento", nullable = false)
    private FormaPagamento formaPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "usuario_id",
        foreignKey = @ForeignKey(name = "fk_vendas_usuario")
    )
    private Usuario comprador;

    @Column(name = "observacao")
    private String observacao;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;
    
}
