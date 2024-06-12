package com.gestordetarefas.pessoa.application.api;

import jakarta.validation.constraints.*;

public record NovaPessoaRequest (
        @NotBlank
        String nome,
        @DepartamentoValido
        String departamento
) {}
