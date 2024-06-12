package com.gestordetarefas.pessoa.application.service;

import com.gestordetarefas.pessoa.application.api.*;
import jakarta.transaction.*;
import org.springframework.stereotype.*;

@Service
public interface PessoaService {
    @Transactional
    NovaPessoaResponse adicionarNovaPessoa(NovaPessoaRequest novaPessoaRequest);
}
