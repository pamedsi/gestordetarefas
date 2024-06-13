package com.gestordetarefas.tarefa.application.api;

import jakarta.validation.*;
import org.hibernate.validator.constraints.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/tarefas")
public interface TarefaAPI {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TarefaCriadaResponse postNovaTarefa(@RequestBody @Valid CriarTarefaRequest tarefaDTO);

    @PatchMapping("alocar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void patchAlocaPessoa(@PathVariable("id") @UUID(message = "UUID da tarefa inválido!") String identificador, @RequestBody @Valid PessoaAlocadaRequest pessoaAlocada);

    @PatchMapping("finalizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void patchFinalizaTarefa(@PathVariable("id") @UUID(message = "UUID da tarefa inválido!") String identificador);
}