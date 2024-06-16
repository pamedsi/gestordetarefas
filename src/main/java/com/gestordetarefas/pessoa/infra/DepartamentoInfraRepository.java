package com.gestordetarefas.pessoa.infra;

import com.gestordetarefas.departamento.domain.*;
import com.gestordetarefas.exception.*;
import com.gestordetarefas.pessoa.application.repository.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
@Log4j2
@RequiredArgsConstructor
public class DepartamentoInfraRepository implements DepartamentoRepository {
    private final DepartamentoJPARepository departamentoJPARepository;

    @Override
    public Departamento buscaDepartamentoPorIdentificador(String identificadorDoDepartamento) {
        log.info("[inicia]  DepartamentoInfraRepository - buscaDepartamentoPorIdentificador");
        Departamento departamento = departamentoJPARepository.findByIdentificador(UUID.fromString(identificadorDoDepartamento)).orElseThrow(
                () -> new APIException("Departamento não encontrado!", HttpStatus.NOT_FOUND)
        );
        log.info("[finaliza]  DepartamentoInfraRepository - buscaDepartamentoPorIdentificador");
        return departamento;
    }

    @Override
    public Page<Departamento> buscaTodosOsDepartamentos(Pageable pageable) {
        log.info("[inicia]  DepartamentoInfraRepository - buscaTodosOsDepartamentos");
        Page<Departamento> departamentos = departamentoJPARepository.findAllByDeletadoFalse(pageable);
        log.info("[finaliza]  DepartamentoInfraRepository - buscaTodosOsDepartamentos");
        return departamentos;
    }
}