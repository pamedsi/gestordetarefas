package com.gestordetarefas.pessoa.application.api;

import com.gestordetarefas.departamento.domain.*;
import com.gestordetarefas.pessoa.domain.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;

import java.util.*;

public record DetalhesDaPessoa (
        String nome,
        String departamento,
        int totalDeHorasGastasNasTarefas
) {
    public DetalhesDaPessoa (Pessoa pessoa) {
        this (
                pessoa.getNome(),
                pessoa.getDepartamento().getNome(),
                pessoa.getHorasTrabalhadas()
        );
    }

    private static List<DetalhesDaPessoa> converterVariosParaDTO(List<Pessoa> tarefa) {
        return tarefa.stream().map(DetalhesDaPessoa::new).toList();
    }

    public static PagedModel<DetalhesDaPessoa> converterParaPageDTO(Page<Pessoa> page) {
        List<DetalhesDaPessoa> listaDeDTO = converterVariosParaDTO(page.getContent());
        return new PagedModel<>(new PageImpl<>(listaDeDTO, page.getPageable(), page.getTotalElements()));
    }
}
