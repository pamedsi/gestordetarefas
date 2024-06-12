package com.gestordetarefas.colaborador.application.api;

import jakarta.validation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/colaborador")
public interface ColaboradorAPI {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    NovoColaboradorResponse postNovoColaborador(@RequestBody @Valid NovoColaboradorRequest colaboradorDTO);
}
