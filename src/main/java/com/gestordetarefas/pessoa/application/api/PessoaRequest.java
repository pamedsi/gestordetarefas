package com.gestordetarefas.pessoa.application.api;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.*;

public record PessoaRequest (
        @NotBlank
        String nome,
        @UUID (message = "UUID do departamento inválido!")
        String identificadorDoDepartamento
) {}