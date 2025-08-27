package com.linktic.api.infrastructure.errors;

import lombok.Getter;

@Getter
public class NegocioException extends RuntimeException {

    private final String codigo;
    private final String mensaje;
    private final String detalle;

    public NegocioException(String codigo, String mensaje, String detalle) {
        super(mensaje);
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.detalle = detalle;
    }
}