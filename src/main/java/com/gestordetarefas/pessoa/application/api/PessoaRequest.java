package com.gestordetarefas.pessoa.application.api;

import jakarta.validation.constraints.*;

public record PessoaRequest (
        @NotBlank
        String nome,
        @DepartamentoValido
        String departamento
) {}
