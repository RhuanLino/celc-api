package com.celc.api.dtos;

public class LoginResponseDTO {
    
    private boolean sucesso;
    private String mensagem;
    private String token;

    public LoginResponseDTO(boolean sucesso, String mensagem, String token) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.token = token;
    }

    public boolean isSucesso() {
        return sucesso;
    }
    
    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

