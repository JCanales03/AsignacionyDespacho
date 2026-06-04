package com.conaf.microservicio.despachos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DespachoException extends RuntimeException {
    public DespachoException(String message) {
        super(message);
    }
}
