package com.gestordetarefas.pessoa.application.repository;

import com.gestordetarefas.departamento.domain.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Repository
public interface DepartamentoRepository {
    Departamento buscaDepartamentoPorIdentificador(String identificadorDoDepartamento);
    Page<Departamento> buscaTodosOsDepartamentos(Pageable pageable);
}