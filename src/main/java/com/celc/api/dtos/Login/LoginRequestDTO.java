package com.celc.api.dtos.Login;

public class LoginRequestDTO {
    private String username;
    private String senha;

    public LoginRequestDTO() {
    }

    public LoginRequestDTO(String username, String senha) {
        this.username = username;
        this.senha = senha;
    }

    public String getUsername() {
        return username;
    }

    public String getSenha() {
        return senha;
    }
}
