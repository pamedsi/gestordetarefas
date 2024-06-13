package com.gestordetarefas.tarefa.application.api;

import com.fasterxml.jackson.annotation.*;
import com.gestordetarefas.pessoa.application.api.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.*;

import java.time.*;

public record CriarTarefaRequest (
        @Size(min = 1, max = 255, message = "Campo 'titulo' não pode ser maior que 255 caracteres.")
        @NotBlank (message = "Campo 'titulo' não pode faltar.")
        String titulo,
        String descricao,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate prazo,
        @DepartamentoValido
        String departamento,
        @NumberFormat
        int duracao
) {}
