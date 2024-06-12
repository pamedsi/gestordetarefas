package com.gestordetarefas.pessoa.infra;

import com.gestordetarefas.pessoa.application.repository.*;
import com.gestordetarefas.pessoa.domain.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.stereotype.*;

@Repository
@RequiredArgsConstructor
@Log4j2
public class PessoaInfraRepository implements PessoaRepository {
    @Override
    public void salvarNovaPessoa(Pessoa pessoa) {
        log.info("[inicia]  PessoaInfraRepository - salvarNovaPessoa");
        log.info("[finaliza]  PessoaInfraRepository - salvarNovaPessoa\n");
    }
}
