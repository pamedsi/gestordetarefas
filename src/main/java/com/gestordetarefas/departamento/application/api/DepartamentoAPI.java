package com.gestordetarefas.departamento.application.api;

import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/departamentos")
public interface DepartamentoAPI {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    PagedModel<DetalhesDoDepartamento> getDepartamentos(@PageableDefault (direction = Sort.Direction.ASC, sort = {"nome"}) Pageable pageable);
}