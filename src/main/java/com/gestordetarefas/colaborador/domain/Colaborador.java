package com.gestordetarefas.colaborador.domain;

import com.gestordetarefas.tarefa.domain.*;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private UUID identificador;
    @Column
    private String nome;
    @Column
    private String departamento;
    @OneToMany(mappedBy = "colaboradorAlocado")
    private List<Tarefa> tarefas;
}
