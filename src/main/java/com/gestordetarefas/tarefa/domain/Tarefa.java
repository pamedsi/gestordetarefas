package com.gestordetarefas.tarefa.domain;

import com.gestordetarefas.pessoa.domain.*;
import com.gestordetarefas.tarefa.application.api.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;
import java.util.*;

@Entity
@NoArgsConstructor
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Getter
    private UUID identificador;
    @Column
    private String titulo;
    @Column (columnDefinition = "TEXT")
    private String descricao;
    @Column
    private LocalDate prazo;
    @Column
    @Enumerated(EnumType.STRING)
    private Departamento departamento;
    @Column
    private int duracao;
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoaAlocada;
    @Column
    private boolean finalizada;

    public Tarefa(CriarTarefaRequest tarefaDTO) {
        identificador = UUID.randomUUID();
        titulo = tarefaDTO.titulo();
        descricao = tarefaDTO.descricao();
        prazo = tarefaDTO.prazo();
        departamento = Departamento.valueOf(tarefaDTO.departamento());
        duracao = tarefaDTO.duracao();
        finalizada = false;
    }
}
