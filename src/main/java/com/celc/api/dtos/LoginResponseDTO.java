package com.celc.api.dtos;

public class LoginResponseDTO {
    
    private String token;
    private boolean sucesso;
    private String mensagem;
    private Long trabalhadorId;

    public LoginResponseDTO(boolean sucesso, String mensagem, Long trabalhadorId, String token) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.trabalhadorId = trabalhadorId;
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

    public Long getTrabalhadorId() {
        return trabalhadorId;
    }

    public void setTrabalhadorId(Long trabalhadorId) {
        this.trabalhadorId = trabalhadorId;
    }
}

