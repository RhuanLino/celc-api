package com.celc.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name= "trabalhador")
public class Trabalhador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String nome_espiritual;
    private String email;
    private String senha;
    private Boolean desligado;

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
