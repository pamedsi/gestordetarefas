package com.gestordetarefas.departamento.application.service;

import com.gestordetarefas.departamento.application.api.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.stereotype.*;

@Service
public interface DepartamentoService {
    PagedModel<DetalhesDoDepartamento> buscaTodosOsDepartamentos(Pageable pageable);
}