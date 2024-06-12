package com.gestordetarefas.tarefa.domain;

import com.gestordetarefas.colaborador.domain.*;
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
    @Column
    private String descricao;
    @Column
    private LocalDateTime prazo;
    @Column
    private String departamento;
    @Column
    private LocalDateTime duracao;
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoaAlocado;
    @Column
    private boolean finalizada;
}
