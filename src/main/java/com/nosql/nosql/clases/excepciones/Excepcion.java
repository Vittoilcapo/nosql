package com.nosql.nosql.clases.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class Excepcion extends RuntimeException {
    public Excepcion(String mensaje) {
        super(mensaje);
    }
}
