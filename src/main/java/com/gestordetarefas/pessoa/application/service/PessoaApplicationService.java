package com.gestordetarefas.pessoa.application.service;

import com.gestordetarefas.pessoa.application.api.*;
import com.gestordetarefas.pessoa.application.repository.*;
import com.gestordetarefas.pessoa.domain.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.stereotype.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class PessoaApplicationService implements PessoaService {
    private final PessoaRepository pessoaRepository;

    @Override
    public NovaPessoaResponse adicionaNovaPessoa(NovaPessoaRequest novaPessoaDTO) {
        log.info("[inicia]  PessoaApplicationService - adicionaNovaPessoa");
        Pessoa pessoa = new Pessoa(novaPessoaDTO);
        pessoaRepository.salvaNovaPessoa(pessoa);
        log.info("[finaliza]  PessoaApplicationService - adicionaNovaPessoa");
        return new NovaPessoaResponse(pessoa.getIdentificador());
    }
}
