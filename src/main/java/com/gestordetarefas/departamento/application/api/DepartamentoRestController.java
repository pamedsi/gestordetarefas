package com.gestordetarefas.departamento.application.api;

import com.gestordetarefas.departamento.application.service.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequiredArgsConstructor
public class DepartamentoRestController implements DepartamentoAPI {
    private final DepartamentoService departamentoService;

    @Override
    public PagedModel<DetalhesDoDepartamento> getDepartamentos(Pageable pageable) {
        log.info("[inicia]  DepartamentoRestController - getDepartamentos");
        PagedModel<DetalhesDoDepartamento> departamentos = departamentoService.buscaTodosOsDepartamentos(pageable);
        log.info("[finaliza]  DepartamentoRestController - getDepartamentos\n");
        return departamentos;
    }
}