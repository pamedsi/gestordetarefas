package com.gestordetarefas.tarefa.infra;

import com.gestordetarefas.departamento.domain.*;
import com.gestordetarefas.exception.*;
import com.gestordetarefas.tarefa.application.repository.*;
import com.gestordetarefas.tarefa.domain.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import java.util.*;

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

    @Override
    public Tarefa buscaTarefaPorIdentificador(String identificador) {
        log.info("[inicia]  TarefaInfraRepository - buscaTarefaPorIdentificador");
        Tarefa tarefa = tarefaJPARepository.findByIdentificadorAndDeletadaFalse(UUID.fromString(identificador))
                .orElseThrow(() -> new APIException("Tarefa não encontrada!", HttpStatus.NOT_FOUND)
        );
        log.info("[finaliza]  TarefaInfraRepository - buscaTarefaPorIdentificador");
        return tarefa;
    }

    @Override
    public Page<Tarefa> buscaTarefasPendentes(Pageable pageable) {
        log.info("[inicia]  TarefaInfraRepository - buscaTarefasPendentes");
        Page<Tarefa> tarefas = tarefaJPARepository.findAllByDeletadaFalseAndPessoaAlocadaNullOrderByPrazoAsc(pageable);
        log.info("[finaliza]  TarefaInfraRepository - buscaTarefasPendentes");
        return tarefas;
    }

    @Override
    public int contaTarefasPorDepartamento(Departamento departamento) {
        log.info("[inicia]  TarefaInfraRepository - contaTarefasPorDepartamento");
        int quantidadeDeTarefas = tarefaJPARepository.countByDepartamento(departamento);
        log.info("[finaliza]  TarefaInfraRepository - contaTarefasPorDepartamento");
        return quantidadeDeTarefas;
    }
}
