package com.gestordetarefas.pessoa.infra;

import com.gestordetarefas.exception.*;
import com.gestordetarefas.pessoa.application.repository.*;
import com.gestordetarefas.pessoa.domain.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import java.util.*;

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

    @Override
    public Pessoa buscaPessoaPorIdentificador(String identificador) {
        log.info("[inicia]  PessoaInfraRepository - buscaPessoaPorIdentificador");
        Pessoa pessoa = pessoaJPARepository.findByIdentificadorAndDeletadaFalse(UUID.fromString(identificador)).orElseThrow(
                () -> new APIException("Pessoa não encontrada.", HttpStatus.NOT_FOUND)
        );
        log.info("[finaliza]  PessoaInfraRepository - buscaPessoaPorIdentificador");
        return pessoa;
    }

    @Override
    public Page<Pessoa> buscaPessoas(Pageable pageable) {
        log.info("[inicia]  PessoaInfraRepository - buscaPessoas");
        Page<Pessoa> pessoas = pessoaJPARepository.findAllByDeletadaFalse(pageable);
        log.info("[finaliza]  PessoaInfraRepository - buscaPessoas");
        return pessoas;
    }
}
