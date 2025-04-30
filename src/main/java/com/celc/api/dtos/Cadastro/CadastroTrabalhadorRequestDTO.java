package com.celc.api.dtos.Cadastro;

public class CadastroTrabalhadorRequestDTO {
    private String nome;
    private String nome_espiritual;
    private String email;
    private String senha;

    public CadastroTrabalhadorRequestDTO() {}

    public CadastroTrabalhadorRequestDTO(String nome, String nome_espiritual, String email, String senha) {
        this.nome = nome;
        this.nome_espiritual = nome_espiritual;
        this.email = email;
        this.senha = senha;
    }

    // Getters and Setters

    public String getNome() {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public String getNomeEspiritual() {
        return nome_espiritual;
    }

    public void setNomeEspiritual (String nome_espiritual) {
        this.nome_espiritual = nome_espiritual;
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
