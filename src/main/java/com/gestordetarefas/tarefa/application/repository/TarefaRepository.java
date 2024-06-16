package com.gestordetarefas.tarefa.application.repository;

import com.gestordetarefas.departamento.domain.*;
import com.gestordetarefas.tarefa.domain.*;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.*;

@Repository
public interface TarefaRepository {
    void salvaNovaTarefa(Tarefa tarefa);
    Tarefa buscaTarefaPorIdentificador(String identificador);
    Page<Tarefa> buscaTarefasPendentes(Pageable pageable);
    int contaTarefasPorDepartamento(Departamento departamento);
}