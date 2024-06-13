package com.gestordetarefas.tarefa.application.service;

import com.gestordetarefas.tarefa.application.api.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
public interface TarefaService {
    TarefaCriadaResponse adicionaTarefa(CriarTarefaRequest tarefaDTO);
    @Transactional
    void alocaTarefa(String identificador, PessoaAlocadaRequest pessoaAlocada);
}
