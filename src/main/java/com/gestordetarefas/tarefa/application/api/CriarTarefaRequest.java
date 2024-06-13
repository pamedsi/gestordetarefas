package com.gestordetarefas.tarefa.application.api;

import com.gestordetarefas.pessoa.application.api.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotBlank;
import jdk.jfr.*;
import org.hibernate.validator.constraints.*;
import org.springframework.format.annotation.*;

import java.time.*;

public record CriarTarefaRequest (
        @Size(min = 1, max = 255)
        @NotBlank
        String titulo,
        String descricao,
        @DateTimeFormat
        LocalDate prazo,
        @DepartamentoValido
        String departamento,
        @NumberFormat
        int duracao,
        @UUID (message = "UUID da pessoa alocada inválido!")
        String pessoaAlocada,
        @BooleanFlag
        boolean finalizada
) {}
