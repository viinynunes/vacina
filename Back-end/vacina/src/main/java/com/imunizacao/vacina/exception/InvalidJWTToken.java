package com.imunizacao.vacina.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidJWTToken extends RuntimeException {
    public InvalidJWTToken(String message) {
        super(message);
    }
}
