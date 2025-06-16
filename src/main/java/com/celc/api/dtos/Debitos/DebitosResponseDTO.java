package com.celc.api.dtos.Debitos;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.celc.api.models.Debitos;

public class DebitosResponseDTO {
    private boolean sucesso;
    private String mensagem;
    private List<DebitoDTO> debitos;

    public DebitosResponseDTO(boolean sucesso, String mensagem, List<DebitoDTO> debitos) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.debitos = debitos;
    }
    
    public DebitosResponseDTO(boolean sucesso, String mensagem) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
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

    public List<DebitoDTO> getDebitos() {
        return debitos;
    }

    public void setDebitos(List<DebitoDTO> debitos) {
        this.debitos = debitos;
    }

    public static class DebitoDTO {
        private String tipo;
        private BigDecimal valor;
        private Boolean pago;
        private OffsetDateTime data;

        // Construtor que recebe a entidade Debitos
        public DebitoDTO(Debitos debito) {
            this.tipo = debito.getTipo();
            this.valor = debito.getValor();
            this.pago = debito.getPago();
            this.data = debito.getData();
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public BigDecimal getValor() {
            return valor;
        }

        public void setValor(BigDecimal valor) {
            this.valor = valor;
        }

        public Boolean getPago() {
            return pago;
        }

        public void setPago(Boolean pago) {
            this.pago = pago;
        }

        public OffsetDateTime getData() {
            return data;
        }

        public void setData(OffsetDateTime data) {
            this.data = data;
        }
    }
}
