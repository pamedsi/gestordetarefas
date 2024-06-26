package com.gestordetarefas.tarefa.application.api;

import com.gestordetarefas.tarefa.application.service.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
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

    @Override
    public void patchAlocaPessoa(String identificador, PessoaAlocadaRequest pessoaAlocada) {
        log.info("[inicia]  TarefaRestController - putPessoaAlocada");
        tarefaService.alocaTarefa(identificador, pessoaAlocada);
        log.info("[finaliza]  TarefaRestController - putPessoaAlocada\n");
    }

    @Override
    public void patchFinalizaTarefa(String identificador) {
        log.info("[inicia]  TarefaRestController - patchFinalizaTarefa");
        tarefaService.finalizaTarefa(identificador);
        log.info("[finaliza]  TarefaRestController - patchFinalizaTarefa\n");
    }

    @Override
    public PagedModel<TarefaPendenteDTO> getTarefasPendentes(Pageable pageable) {
        log.info("[inicia]  TarefaRestController - getTarefasPendentes");
        PagedModel<TarefaPendenteDTO> tarefasPendentes = tarefaService.buscaTarefasPendentes(pageable);
        log.info("[finaliza]  TarefaRestController - getTarefasPendentes\n");
        return tarefasPendentes;
    }
}
