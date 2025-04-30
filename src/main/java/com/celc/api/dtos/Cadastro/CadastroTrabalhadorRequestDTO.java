package com.celc.api.dtos.Cadastro;

public class CadastroTrabalhadorRequestDTO {
    private String nome;
    private String nomeEspiritual;
    private String email;
    private String senha;

    public CadastroTrabalhadorRequestDTO() {}

    public CadastroTrabalhadorRequestDTO(String nome, String nomeEspiritual, String email, String senha) {
        this.nome = nome;
        this.nomeEspiritual = nomeEspiritual;
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
