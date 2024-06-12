package com.gestordetarefas.pessoa.application.api;

import jakarta.validation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/api/pessoas")
public interface PessoaAPI {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    NovaPessoaResponse postNovaPessoa(@RequestBody @Valid NovaPessoaRequest colaboradorDTO);
}
