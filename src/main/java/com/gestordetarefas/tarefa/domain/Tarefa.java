package com.gestordetarefas.tarefa.domain;

import com.gestordetarefas.pessoa.domain.*;
import jakarta.persistence.*;

import java.time.*;
import java.util.*;

@Entity
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private UUID identificador;
    @Column
    private String titulo;
    @Column (columnDefinition = "TEXT")
    private String descricao;
    @Column
    private LocalDateTime prazo;
    @Column
    @Enumerated(EnumType.STRING)
    private Departamento departamento;
    @Column
    private LocalDateTime duracao;
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoaAlocada;
    @Column
    private boolean finalizada;
}
