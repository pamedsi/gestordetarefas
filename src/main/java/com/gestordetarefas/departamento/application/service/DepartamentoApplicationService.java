package com.gestordetarefas.departamento.application.service;

import com.gestordetarefas.departamento.application.api.*;
import com.gestordetarefas.departamento.domain.*;
import com.gestordetarefas.pessoa.application.repository.*;
import com.gestordetarefas.tarefa.application.repository.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class DepartamentoApplicationService implements DepartamentoService {
    private final TarefaRepository tarefaRepository;
    private final PessoaRepository pessoaRepository;
    private final DepartamentoRepository departamentoRepository;

    @Override
    public PagedModel<DetalhesDoDepartamento> buscaTodosOsDepartamentos(Pageable pageable) {
        log.info("[inicia] DepartamentoApplicationService - buscaDepartamentos");
        Page<Departamento> departamentos = departamentoRepository.buscaTodosOsDepartamentos(pageable);
        List<DetalhesDoDepartamento> departamentosDTO = departamentos.getContent().stream().map(
                (departamento) -> {
                    int quantidadeDePessoas = pessoaRepository.contaPessoasPorDepartamento(departamento);
                    int quantidadeDeTarefas = tarefaRepository.contaTarefasPorDepartamento(departamento);
                    return new DetalhesDoDepartamento(departamento, quantidadeDePessoas, quantidadeDeTarefas);
                }
        ).toList();
        PagedModel<DetalhesDoDepartamento> pagedModel = DetalhesDoDepartamento.converterParaPageDTO(departamentos, departamentosDTO);
        log.info("[finaliza] DepartamentoApplicationService - buscaDepartamentos");
        return pagedModel;
    }
}
