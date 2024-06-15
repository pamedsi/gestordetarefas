package com.gestordetarefas.pessoa.application.api;

import com.gestordetarefas.pessoa.application.service.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
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

    @Override
    public void deletePessoa(String identificador) {
        log.info("[inicia]  PessoaRestController - deletePessoa");
        pessoaService.deletaPessoa(identificador);
        log.info("[finaliza]  PessoaRestController - deletePessoa\n");
    }

    @Override
    public PagedModel<DetalhesDaPessoa> getPessoas(Pageable pageable) {
        log.info("[inicia]  PessoaRestController - getPessoas");
        PagedModel<DetalhesDaPessoa> pessoas = pessoaService.buscaPessoas(pageable);
        log.info("[finaliza]  PessoaRestController - getPessoas\n");
        return pessoas;
    }

    @Override
    public PagedModel<DetalhesDaPessoaComMediaDeHorasGastas> getPessoasPorPeriodo(String nome, Pageable pageable) {
        log.info("[inicia]  PessoaRestController - getPessoasPorPeriodo");
        PagedModel<DetalhesDaPessoaComMediaDeHorasGastas> pessoas = pessoaService.buscaPessoasPorNome(nome, pageable);
        log.info("[finaliza]  PessoaRestController - getPessoasPorPeriodo\n");
        return pessoas;
    }
}
