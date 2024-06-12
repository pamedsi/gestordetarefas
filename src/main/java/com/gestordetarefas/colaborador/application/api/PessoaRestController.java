package com.gestordetarefas.colaborador.application.api;

import lombok.extern.log4j.*;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class PessoaRestController implements ColaboradorAPI{
    @Override
    public NovaPessoaResponse postNovaPessoa(NovaPessoaRequest colaboradorDTO) {
        log.info("[inicia]  PessoaRestController - postNovoColaborador");
        log.info("[finaliza]  PessoaRestController - postNovoColaborador\n");
        return null;
    }
}
