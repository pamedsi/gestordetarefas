package com.gestordetarefas.tarefa.application.api;

import org.hibernate.validator.constraints.*;

public record PessoaAlocadaRequest(
        @UUID (message = "UUID da pessoa inválido!")
        String identificadorDaPessoa
) {}