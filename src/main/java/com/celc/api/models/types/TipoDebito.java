package com.celc.api.models.types;

public enum TipoDebito {
    LIVRARIA,
    MENSALIDADE;

    public static TipoDebito fromString(String value) {
        return value == null ? null : TipoDebito.valueOf(value.toUpperCase());
    }
}
