package com.gestordetarefas.tarefa.application.service;

import com.gestordetarefas.pessoa.application.repository.*;
import com.gestordetarefas.pessoa.domain.*;
import com.gestordetarefas.tarefa.application.api.*;
import com.gestordetarefas.tarefa.application.repository.*;
import com.gestordetarefas.tarefa.domain.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.web.*;
import org.springframework.stereotype.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class TarefaApplicationService implements TarefaService {
    private final TarefaRepository tarefaRepository;
    private final PessoaRepository pessoaRepository;

    @Override
    public TarefaCriadaResponse adicionaTarefa(CriarTarefaRequest tarefaDTO) {
        log.info("[inicia]  TarefaApplicationService - adicionaTarefa");
        Tarefa tarefa = new Tarefa(tarefaDTO);
        tarefaRepository.salvaNovaTarefa(tarefa);
        log.info("[finaliza]  TarefaApplicationService - adicionaTarefa");
        return new TarefaCriadaResponse(tarefa.getIdentificador());
    }

    @Override
    public void alocaTarefa(String identificador, PessoaAlocadaRequest pessoaAlocada) {
        log.info("[inicia]  TarefaApplicationService - alocaTarefa");
        Tarefa tarefa = tarefaRepository.buscaTarefaPorIdentificador(identificador);
        Pessoa pessoa = pessoaRepository.buscaPessoaPorIdentificador(pessoaAlocada.identificadorDaPessoa());
        tarefa.aloca(pessoa);
        log.info("[finaliza]  TarefaApplicationService - alocaTarefa");
    }

    @Override
    public void finalizaTarefa(String identificador) {
        log.info("[inicia]  TarefaApplicationService - finalizaTarefa");
        Tarefa tarefa = tarefaRepository.buscaTarefaPorIdentificador(identificador);
        tarefa.finaliza();
        log.info("[finaliza]  TarefaApplicationService - finalizaTarefa");
    }

    @Override
    public PagedModel<TarefaPendenteDTO> buscaTarefasPendentes(Pageable pageable) {
        log.info("[inicia]  TarefaApplicationService - buscaTarefasPendentes");
        Page<Tarefa> tarefasEmPersistencia = tarefaRepository.buscaTarefasPendentes(pageable);
        PagedModel<TarefaPendenteDTO> pessoasEmDTO = TarefaPendenteDTO.converterParaPageDTO(tarefasEmPersistencia);
        log.info("[finaliza]  TarefaApplicationService - buscaTarefasPendentes");
        return pessoasEmDTO;
    }
}
