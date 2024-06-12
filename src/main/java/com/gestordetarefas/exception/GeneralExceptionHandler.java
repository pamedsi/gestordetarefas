package com.gestordetarefas.exception;

import jakarta.servlet.http.*;
import lombok.extern.log4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;

@RestControllerAdvice
@Log4j2
public class GeneralExceptionHandler {
    @ExceptionHandler(APIException.class)
    public ResponseEntity<DetalhesDaException> APIExceptionHandler(APIException exception, HttpServletRequest request) {
        log.error(exception.getHttpStatus());
        log.error(exception.getMessage());
        return ResponseEntity.status(exception.getHttpStatus()).body(
                new DetalhesDaException(
                        exception.getHttpStatus().toString(),
                        exception.getMessage(),
                        exception.getHttpStatusInNumber(),
                        LocalDateTime.now().toString(),
                        request.getServletPath(),
                        request.getMethod()
                ));
    }
}
