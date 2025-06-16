package com.celc.api.dtos.Debitos;

import java.math.BigDecimal;
import java.security.Timestamp;

public class DebitosRequestDTO {
    private String tipo;
    private Boolean pago;
    private BigDecimal valor;
    private Timestamp data;

    public DebitosRequestDTO() {}

    public DebitosRequestDTO (String tipo, Boolean pago, BigDecimal valor, Timestamp data) {
        this.tipo = tipo;
        this.pago = pago;
        this.valor = valor;
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public Boolean getPago() {
        return pago;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Timestamp getData() {
        return data;
    }
}
