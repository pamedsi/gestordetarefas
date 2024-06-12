package com.gestordetarefas.pessoa.application.api;

import jakarta.validation.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidadorDeDepartamento.class)
public @interface DepartamentoValido {

    String message() default "Departamento inválido! Departamentos permitidos: 'FINANCEIRO', 'COMERCIAL' e 'DESENVOLVIMENTO'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

class ValidadorDeDepartamento implements ConstraintValidator<DepartamentoValido, String> {
    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {
        if (valor == null || valor.isEmpty()) return false;
        String[] palavras = {"FINANCEIRO", "COMERCIAL", "DESENVOLVIMENTO"};
        for (String palavra : palavras) {
            if (valor.equalsIgnoreCase(palavra)) return true;
        }
        return false;
    }
}

