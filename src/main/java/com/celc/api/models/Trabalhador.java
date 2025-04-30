package com.celc.api.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "trabalhador")
public class Trabalhador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(name = "nome_espiritual", length = 255)
    private String nomeEspiritual;

    @Column(nullable = false, length = 255, unique = true)
    private String email;

    @Column(nullable = false, length = 255)
    private String senha;

    @Column(columnDefinition = "TINYINT(1) default 0")
    private Boolean desligado = false;

    @OneToMany(mappedBy = "trabalhador")
    private List<Debitos> debitos = new ArrayList<>();

    @OneToMany(mappedBy = "trabalhador")
    private List<Frequencia> frequencias = new ArrayList<>();

    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public String getNomeEspiritual() {
        return nomeEspiritual;
    }

    public void setNomeEspiritual (String nomeEspiritual) {
        this.nomeEspiritual = nomeEspiritual;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha (String senha) {
        this.senha = senha;
    }
}
