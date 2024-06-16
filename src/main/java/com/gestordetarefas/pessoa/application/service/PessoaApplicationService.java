package com.gestordetarefas.pessoa.application.service;

import com.gestordetarefas.departamento.domain.*;
import com.gestordetarefas.pessoa.application.api.*;
import com.gestordetarefas.pessoa.application.repository.*;
import com.gestordetarefas.pessoa.domain.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.stereotype.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class PessoaApplicationService implements PessoaService {
    private final PessoaRepository pessoaRepository;
    private final DepartamentoRepository departamentoRepository;

    @Override
    public NovaPessoaResponse adicionaNovaPessoa(PessoaRequest novaPessoaDTO) {
        log.info("[inicia]  PessoaApplicationService - adicionaNovaPessoa");
        Departamento departamento = departamentoRepository.buscaDepartamentoPorIdentificador(novaPessoaDTO.identificadorDoDepartamento());
        Pessoa pessoa = new Pessoa(novaPessoaDTO, departamento);
        pessoaRepository.salvaNovaPessoa(pessoa);
        log.info("[finaliza]  PessoaApplicationService - adicionaNovaPessoa");
        return new NovaPessoaResponse(pessoa.getIdentificador());
    }

    @Override
    public void alteraPessoa(PessoaRequest pessoaRequest, String identificador) {
        log.info("[inicia]  PessoaApplicationService - alteraPessoa");
        Departamento departamento = departamentoRepository.buscaDepartamentoPorIdentificador(pessoaRequest.identificadorDoDepartamento());
        Pessoa pessoa = pessoaRepository.buscaPessoaPorIdentificador(identificador);
        pessoa.atualiza(pessoaRequest, departamento);
        log.info("[finaliza]  PessoaApplicationService - alteraPessoa");
    }

    @Override
    public void deletaPessoa(String identificador) {
        log.info("[inicia]  PessoaApplicationService - deletaPessoa");
        Pessoa pessoa = pessoaRepository.buscaPessoaPorIdentificador(identificador);
        pessoa.deleta();
        log.info("[finaliza]  PessoaApplicationService - deletaPessoa");
    }

    @Override
    public PagedModel<DetalhesDaPessoa> buscaPessoas(Pageable pageable) {
        log.info("[inicia]  PessoaApplicationService - buscaPessoas");
        Page<Pessoa> pessoasEmPersistencia = pessoaRepository.buscaPessoas(pageable);
        PagedModel<DetalhesDaPessoa> pessoasEmDTO = DetalhesDaPessoa.converterParaPageDTO(pessoasEmPersistencia);
        log.info("[finaliza]  PessoaApplicationService - buscaPessoas");
        return pessoasEmDTO;
    }

    @Override
    public PagedModel<DetalhesDaPessoaComMediaDeHorasGastas> buscaPessoasPorNome(String nome, Pageable pageable) {
        log.info("[inicia]  PessoaApplicationService - buscaPessoasPorNome");
        Page<Pessoa> pessoasEmPersistencia = pessoaRepository.buscaPessoasPorNome(nome, pageable);
        var pessoasEmDTO = DetalhesDaPessoaComMediaDeHorasGastas.converterParaPageDTO(pessoasEmPersistencia);
        log.info("[finaliza]  PessoaApplicationService - buscaPessoasPorNome");
        return pessoasEmDTO;
    }
}
