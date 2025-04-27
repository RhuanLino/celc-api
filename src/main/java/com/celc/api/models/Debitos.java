package com.celc.api.models;

import java.math.BigDecimal;

import com.celc.api.models.types.TipoDebito;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "debitos")
public class Debitos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trabalhador_id", nullable = false)
    private Trabalhador trabalhador;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('LIVRARIA', 'MENSALIDADE')", nullable = false)
    private TipoDebito tipo;

    @Column(nullable = false, precision = 10, scale = 0)
    private BigDecimal valor;

    @Column(nullable = false)
    private Boolean pago;

    // Getters and Setters
}
