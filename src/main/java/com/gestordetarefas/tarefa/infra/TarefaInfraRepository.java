package com.gestordetarefas.tarefa.infra;

import com.gestordetarefas.exception.*;
import com.gestordetarefas.tarefa.application.repository.*;
import com.gestordetarefas.tarefa.domain.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

@Repository
@Log4j2
@RequiredArgsConstructor
public class TarefaInfraRepository implements TarefaRepository {
    private final TarefaJPARepository tarefaJPARepository;

    @Override
    public void salvaNovaTarefa(Tarefa tarefa) {
        log.info("[inicia]  TarefaInfraRepository - salvaNovaTarefa");
        try {
            tarefaJPARepository.save(tarefa);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new APIException("Erro ao salvar no banco!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("[finaliza]  TarefaInfraRepository - salvaNovaTarefa");
    }
}
