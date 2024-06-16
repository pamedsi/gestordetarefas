package com.gestordetarefas.tarefa.application.api;

import com.gestordetarefas.tarefa.domain.*;
import jakarta.persistence.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;

import java.time.*;
import java.util.*;

public record TarefaPendenteDTO(
        UUID identificador,
        String titulo,
        String descricao,
        LocalDate prazo,
        @Enumerated(EnumType.STRING)
        Departamento departamento,
        int duracaoEmHoras
) {
    public TarefaPendenteDTO(Tarefa tarefa){
        this(
                tarefa.getIdentificador(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getPrazo(),
                tarefa.getDepartamento(),
                tarefa.getDuracaoEmHoras()
        );
    }

    public static PagedModel<TarefaPendenteDTO> converterParaPageDTO(Page<Tarefa> page) {
        List<TarefaPendenteDTO> listaDeDTO = converterVariosParaDTO(page.getContent());
        return new PagedModel<>(new PageImpl<>(listaDeDTO, page.getPageable(), page.getTotalElements()));
    }

    private static List<TarefaPendenteDTO> converterVariosParaDTO(List<Tarefa> tarefa) {
        return tarefa.stream().map(TarefaPendenteDTO::new).toList();
    }
}