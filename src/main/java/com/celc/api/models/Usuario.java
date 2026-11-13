package com.celc.api.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.celc.api.models.types.EstadoCivil;
import com.celc.api.models.types.Medium;

import jakarta.persistence.*;

@Entity
@Table(
    name = "usuarios",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_usuarios_username", columnNames = "username"),
        @UniqueConstraint(name = "uk_usuarios_email", columnNames = "email"),
        @UniqueConstraint(name = "uk_usuarios_nome_espiritual", columnNames = "nome_espiritual"),
        @UniqueConstraint(name = "uk_usuarios_cpf", columnNames = "cpf")
    }
)

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "nome_espiritual")
    private String nomeEspiritual;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "senha", length = 60)
    private String senha; // hash BCrypt, nunca texto puro

    @Column(name = "quadro")
    private Integer quadro = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "medium")
    private Medium medium = Medium.ALUNO;

    @Column(name = "pai")
    private String pai;

    @Column(name = "mae")
    private String mae;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @Column(name = "rg", length = 9)
    private String rg;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil")
    private EstadoCivil estadoCivil = EstadoCivil.SOLTEIRO;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "uf", length = 2)
    private String uf;

    @Column(name = "complemento", length = 255)
    private String complemento;

    @Column(name = "ano_ingresso")
    private Integer anoIngresso;

    @Column(name = "valor_mensalidade", precision = 10, scale = 2)
    private BigDecimal valorMensalidade;

    @Column(name = "desligado", nullable = false)
    private boolean desligado = false;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;

    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeEspiritual() {
        return nomeEspiritual;
    }

    public void setNomeEspiritual(String nomeEspiritual) {
        this.nomeEspiritual = nomeEspiritual;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}