package com.gestordetarefas.pessoa.application.repository;

import com.gestordetarefas.pessoa.domain.*;
import org.springframework.stereotype.*;

@Repository
public interface PessoaRepository {
    void salvaNovaPessoa(Pessoa pessoa);
}
