package com.gestordetarefas.pessoa.application.api;

import com.gestordetarefas.pessoa.application.service.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequiredArgsConstructor
public class PessoaRestController implements PessoaAPI {
    private final PessoaService pessoaService;
    @Override
    public NovaPessoaResponse postNovaPessoa(NovaPessoaRequest colaboradorDTO) {
        log.info("[inicia]  PessoaRestController - postNovoColaborador");
        NovaPessoaResponse novaPessoaResponse = pessoaService.adicionaNovaPessoa(colaboradorDTO);
        log.info("[finaliza]  PessoaRestController - postNovoColaborador\n");
        return novaPessoaResponse;
    }
}
