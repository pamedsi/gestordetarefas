package com.gestordetarefas.tarefa.application.service;

import com.gestordetarefas.tarefa.application.api.*;
import org.springframework.stereotype.*;

@Service
public interface TarefaService {
    TarefaCriadaResponse adicionaTarefa(CriarTarefaRequest tarefaDTO);
}
