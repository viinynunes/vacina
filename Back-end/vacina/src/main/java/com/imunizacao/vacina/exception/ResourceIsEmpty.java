package com.imunizacao.vacina.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class ResourceIsEmpty extends RuntimeException {

    public ResourceIsEmpty(String message) {
        super(message);
    }
}
