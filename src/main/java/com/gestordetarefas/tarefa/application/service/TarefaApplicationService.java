package com.gestordetarefas.tarefa.application.service;

import com.gestordetarefas.tarefa.application.api.*;
import com.gestordetarefas.tarefa.application.repository.*;
import com.gestordetarefas.tarefa.domain.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.stereotype.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class TarefaApplicationService implements TarefaService {
    private final TarefaRepository tarefaRepository;

    @Override
    public TarefaCriadaResponse adicionaTarefa(CriarTarefaRequest tarefaDTO) {
        log.info("[inicia]  TarefaApplicationService - adicionaTarefa");
        Tarefa tarefa = new Tarefa(tarefaDTO);
        tarefaRepository.salvaNovaTarefa(tarefa);
        log.info("[finaliza]  TarefaApplicationService - adicionaTarefa");
        return new TarefaCriadaResponse(tarefa.getIdentificador());
    }
}
