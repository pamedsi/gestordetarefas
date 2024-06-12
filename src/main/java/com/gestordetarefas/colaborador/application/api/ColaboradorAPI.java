package com.gestordetarefas.colaborador.application.api;

import jakarta.validation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/pessoas")
public interface ColaboradorAPI {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    NovaPessoaResponse postNovaPessoa(@RequestBody @Valid NovaPessoaRequest colaboradorDTO);
}
