package com.gestordetarefas.pessoa.application.repository;

import com.gestordetarefas.pessoa.domain.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Repository
public interface PessoaRepository {
    void salvaNovaPessoa(Pessoa pessoa);
    Pessoa buscaPessoaPorIdentificador(String identificador);
    Page<Pessoa> buscaPessoas(Pageable pageable);
    Page<Pessoa> buscaPessoasPorNome(String nome, Pageable pageable);
}
