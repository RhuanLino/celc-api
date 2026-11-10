package com.celc.api.models;

import jakarta.persistence.*;

@Entity
@Table(
    name = "espiritos",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_espiritos_nome", columnNames = "nome")
    }
)
public class Espirito {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

}
