package com.gestordetarefas.pessoa.domain;

import com.gestordetarefas.tarefa.domain.*;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private UUID identificador;
    @Column (columnDefinition = "text")
    private String nome;
    @Column
    private String departamento;
    @OneToMany(mappedBy = "pessoaAlocada")
    private List<Tarefa> tarefas;
}