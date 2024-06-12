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
    public NovaPessoaResponse postNovaPessoa(PessoaRequest pessoaRequest) {
        log.info("[inicia]  PessoaRestController - postNovaPessoa");
        NovaPessoaResponse novaPessoaResponse = pessoaService.adicionaNovaPessoa(pessoaRequest);
        log.info("[finaliza]  PessoaRestController - postNovaPessoa\n");
        return novaPessoaResponse;
    }

    @Override
    public void putPessoa(PessoaRequest pessoaRequest, String id) {
        log.info("[inicia]  PessoaRestController - putPessoa");
        pessoaService.alteraPessoa(pessoaRequest, id);
        log.info("[finaliza]  PessoaRestController - putPessoa\n");
    }
}
