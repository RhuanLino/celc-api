package com.celc.api.models;

import jakarta.persistence.*;

@Entity
@Table(
    name = "generos",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_generos_nome", columnNames = "nome")
    }
)
public class Genero {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

}
