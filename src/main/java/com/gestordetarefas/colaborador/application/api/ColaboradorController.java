package com.gestordetarefas.colaborador.application.api;

import lombok.extern.log4j.*;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class ColaboradorController implements ColaboradorAPI{
    @Override
    public NovoColaboradorResponse postNovoColaborador(NovoColaboradorRequest colaboradorDTO) {
        log.info("[inicia]  ColaboradorController - postNovoColaborador");
        log.info("[finaliza]  ColaboradorController - postNovoColaborador\n");
        return null;
    }
}
