package com.gestordetarefas.departamento.infra;

import com.gestordetarefas.exception.*;
import com.gestordetarefas.pessoa.application.repository.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.boot.context.event.*;
import org.springframework.context.event.*;
import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.jdbc.datasource.init.*;
import org.springframework.stereotype.*;

import javax.sql.*;

@Component
@RequiredArgsConstructor
@Log4j2
public class InstanciaDepartamentos {
    private final DataSource dataSource;
    private final DepartamentoRepository departamentoRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void executeScript() {
        ResourceDatabasePopulator populador = new ResourceDatabasePopulator();
        populador.addScript(new ClassPathResource("data.sql"));
        try {
            populador.execute(dataSource);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new APIException("Houve um erro ao popular o banco de dados.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
