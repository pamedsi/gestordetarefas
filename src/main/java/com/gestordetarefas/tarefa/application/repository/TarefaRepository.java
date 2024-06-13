package com.gestordetarefas.tarefa.application.repository;

import com.gestordetarefas.tarefa.domain.*;
import org.springframework.stereotype.*;

@Repository
public interface TarefaRepository {
    void salvaNovaTarefa(Tarefa tarefa);
    Tarefa buscaTarefaPorIdentificador(String identificador);
}