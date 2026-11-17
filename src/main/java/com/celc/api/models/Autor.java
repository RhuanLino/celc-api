package com.celc.api.models;

import jakarta.persistence.*;

@Entity
@Table(
    name = "autores",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_autores_nome", columnNames = "nome")
    }
)
public class Autor {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;
}
