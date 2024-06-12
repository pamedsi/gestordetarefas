package com.gestordetarefas.pessoa.infra;

import com.gestordetarefas.exception.*;
import com.gestordetarefas.pessoa.application.repository.*;
import com.gestordetarefas.pessoa.domain.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

@Repository
@RequiredArgsConstructor
@Log4j2
public class PessoaInfraRepository implements PessoaRepository {
    private final PessoaJPARepository pessoaJPARepository;

    @Override
    public void salvaNovaPessoa(Pessoa pessoa) {
        log.info("[inicia]  PessoaInfraRepository - salvarNovaPessoa");
        try {
            pessoaJPARepository.save(pessoa);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            throw new APIException("Ocorreu um erro. Contate o suporte!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("[finaliza]  PessoaInfraRepository - salvarNovaPessoa");
    }
}
