package com.gestordetarefas.pessoa.application.service;

import com.gestordetarefas.pessoa.application.api.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
public interface PessoaService {
    NovaPessoaResponse adicionaNovaPessoa(PessoaRequest novaPessoaDTO);
    @Transactional
    void alteraPessoa(PessoaRequest pessoaRequest, String identificador);
    @Transactional
    void deletaPessoa(String identificador);
    Page<DetalhesDaPessoa> buscaPessoas(Pageable pageable);
}
