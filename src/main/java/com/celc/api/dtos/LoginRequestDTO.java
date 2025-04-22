package com.celc.api.dtos;

public class LoginRequestDTO {
    private String email;
    private String senha;

    public LoginRequestDTO() {}

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
