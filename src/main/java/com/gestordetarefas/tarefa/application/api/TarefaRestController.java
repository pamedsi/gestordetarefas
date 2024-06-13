package com.gestordetarefas.tarefa.application.api;

import com.gestordetarefas.tarefa.application.service.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequiredArgsConstructor
public class TarefaRestController implements TarefaAPI {
    private final TarefaService tarefaService;

    @Override
    public TarefaCriadaResponse postNovaTarefa(CriarTarefaRequest tarefaDTO) {
        log.info("[inicia]  TarefaRestController - postNovaTarefa");
        TarefaCriadaResponse response = tarefaService.adicionaTarefa(tarefaDTO);
        log.info("[finaliza]  TarefaRestController - postNovaTarefa\n");
        return response;
    }
}
