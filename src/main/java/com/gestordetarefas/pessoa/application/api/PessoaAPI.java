package com.gestordetarefas.pessoa.application.api;

import jakarta.validation.*;
import org.hibernate.validator.constraints.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/pessoas")
public interface PessoaAPI {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    NovaPessoaResponse postNovaPessoa(@RequestBody @Valid PessoaRequest pessoaRequest);

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putPessoa(@RequestBody @Valid PessoaRequest pessoaRequest, @PathVariable("id") @UUID(message = "UUID inválido!") String identificador);

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePessoa(@PathVariable("id") @UUID(message = "UUID inválido!") String identificador);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Page<DetalhesDaPessoa> getPessoas(@PageableDefault(direction = Sort.Direction.ASC, sort = { "nome" }) Pageable pageable);
}
