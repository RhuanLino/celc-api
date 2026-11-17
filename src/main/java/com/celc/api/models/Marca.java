package com.celc.api.models;

import jakarta.persistence.*;

@Entity
@Table(
    name = "marcas",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_marcas_nome", columnNames = "nome")
    }
)
public class Marca {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "ramo")
    private String ramo;

    @Column(name = "cnpj")
    private Long cnpj;

}
