package com.gestordetarefas.tarefa.application.api;

import jakarta.validation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/tarefas")
public interface TarefaAPI {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TarefaCriadaResponse postNovaTarefa(@RequestBody @Valid CriarTarefaRequest tarefaDTO);
}