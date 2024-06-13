package com.gestordetarefas.pessoa.application.api;

import com.gestordetarefas.pessoa.domain.*;
import com.gestordetarefas.tarefa.domain.*;
import org.springframework.data.domain.*;

import java.util.*;

public record DetalhesDaPessoa (
        String nome,
        Departamento departamento,
        int totalDeHorasGastasNasTarefas
) {
    public DetalhesDaPessoa (Pessoa pessoa) {
        this (
                pessoa.getNome(),
                pessoa.getDepartamento(),
                pessoa.getHorasTrabalhadas()
        );
    }

    private static List<DetalhesDaPessoa> converterVariosParaDTO(List<Pessoa> tarefa) {
        return tarefa.stream().map(DetalhesDaPessoa::new).toList();
    }

    public static Page<DetalhesDaPessoa> converterParaPageDTO(Page<Pessoa> page) {
        List<DetalhesDaPessoa> listaDeDTO = converterVariosParaDTO(page.getContent());
        return new PageImpl<>(listaDeDTO, page.getPageable(), page.getTotalElements());
    }
}
