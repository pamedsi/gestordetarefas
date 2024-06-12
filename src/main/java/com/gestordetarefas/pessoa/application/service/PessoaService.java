package com.gestordetarefas.pessoa.application.service;

import com.gestordetarefas.pessoa.application.api.*;
import org.springframework.stereotype.*;

@Service
public interface PessoaService {
    NovaPessoaResponse adicionaNovaPessoa(NovaPessoaRequest novaPessoaDTO);
}
