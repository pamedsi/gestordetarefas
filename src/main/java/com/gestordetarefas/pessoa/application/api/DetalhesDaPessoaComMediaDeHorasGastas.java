package com.gestordetarefas.pessoa.application.api;

import com.gestordetarefas.departamento.domain.*;
import com.gestordetarefas.pessoa.domain.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;

import java.util.*;

public record DetalhesDaPessoaComMediaDeHorasGastas(
        String nome,
        String departamento,
        int mediaDeHorasGastas
) {
    public DetalhesDaPessoaComMediaDeHorasGastas(Pessoa pessoa) {
        this (
                pessoa.getNome(),
                pessoa.getDepartamento().getNome(),
                pessoa.getMediaDeHorasTrabalhadas()
        );
    }

    private static List<DetalhesDaPessoaComMediaDeHorasGastas> converterVariosParaDTO(List<Pessoa> tarefa) {
        return tarefa.stream().map(DetalhesDaPessoaComMediaDeHorasGastas::new).toList();
    }

    public static PagedModel<DetalhesDaPessoaComMediaDeHorasGastas> converterParaPageDTO(Page<Pessoa> page) {
        List<DetalhesDaPessoaComMediaDeHorasGastas> listaDeDTO = converterVariosParaDTO(page.getContent());
        return new PagedModel<>(new PageImpl<>(listaDeDTO, page.getPageable(), page.getTotalElements()));
    }
}
