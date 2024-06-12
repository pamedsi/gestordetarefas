package com.gestordetarefas.exception;

import lombok.*;
import org.springframework.http.*;

@Getter
public class APIException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final int httpStatusInNumber;

    public APIException(String message, HttpStatus status) {
        super(message);
        httpStatus = status;
        httpStatusInNumber = status.value();
    }
}
