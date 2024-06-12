package com.gestordetarefas.exception;

import jakarta.servlet.http.*;
import lombok.extern.log4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.*;

import java.time.*;
import java.util.*;

@RestControllerAdvice
@Log4j2
public class BadRequestExceptionHandler {
    @ExceptionHandler ({HandlerMethodValidationException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<DetalhesDaException> badRequestHandler(Exception exception, HttpServletRequest request) {
        log.info(exception.getClass());
        log.error(exception.getMessage());
        String errors;
        if (exception instanceof HandlerMethodValidationException) {errors = ((HandlerMethodValidationException) exception).getAllErrors().toString();}
        else if (exception instanceof MethodArgumentNotValidException) {errors = ((MethodArgumentNotValidException) exception).getAllErrors().toString();}
        else return null;
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new DetalhesDaException(
                        "Bad Request",
                        getDefaultMessage(errors),
                        400,
                        LocalDateTime.now().toString(),
                        request.getServletPath(),
                        request.getMethod()
                ));
    }

    private String getDefaultMessage(String errorMessage) {
        return List.of(errorMessage.split("default message "))
                .getLast()
                .replaceAll("\\[", "")
                .replaceAll("]", "");
    }
}
