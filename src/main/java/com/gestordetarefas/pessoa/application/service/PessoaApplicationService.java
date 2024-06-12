package com.gestordetarefas.pessoa.application.service;

import com.gestordetarefas.pessoa.application.api.*;
import com.gestordetarefas.pessoa.application.repository.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.stereotype.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class PessoaApplicationService implements PessoaService {
    private final PessoaRepository pessoaRepository;

    @Override
    public NovaPessoaResponse adicionarNovaPessoa(NovaPessoaRequest novaPessoaDTO) {
        log.info("[inicia]  PessoaApplicationService - adicionarNovaPessoa");
        log.info("[finaliza]  PessoaApplicationService - adicionarNovaPessoa\n");
        return new NovaPessoaResponse();
    }
}
