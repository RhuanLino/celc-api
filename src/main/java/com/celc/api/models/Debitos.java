package com.celc.api.models;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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

    @Column(nullable = false, length = 255, unique = true)
    private String tipo;

    @Column(nullable = false, precision = 10, scale = 0)
    private BigDecimal valor;

    @Column(nullable = false)
    private Boolean pago;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private OffsetDateTime data;

    public String getTipo() {
        return tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Boolean getPago() {
        return pago;
    }

    public OffsetDateTime getData() {
        return data;
    }

    @PrePersist
    protected void onCreate() {
        // Define explicitamente o offset para -03:00 (UTC-3)
        this.data = OffsetDateTime.now(ZoneOffset.ofHours(-3));
    }

    // Getters and Setters
}
