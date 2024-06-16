package com.gestordetarefas.departamento.application.api;

import com.gestordetarefas.departamento.domain.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;

import java.util.*;

public record DetalhesDoDepartamento(
     UUID identificador,
     String nome,
     int quantidadeDePessoas,
     int quantidadeDeTarefas
) {
    public DetalhesDoDepartamento(Departamento departamento, int quantidadeDePessoas, int quantidadeDeTarefas){
        this(
                departamento.getIdentificador(),
                departamento.getNome(),
                quantidadeDePessoas,
                quantidadeDeTarefas
        );
    }

    public static PagedModel<DetalhesDoDepartamento> converterParaPageDTO(Page<Departamento> page, List<DetalhesDoDepartamento> detalhesDoDepartamentosDTO) {
        return new PagedModel<>(new PageImpl<>(detalhesDoDepartamentosDTO, page.getPageable(), page.getTotalElements()));
    }
}