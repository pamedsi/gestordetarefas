package com.gestordetarefas.pessoa.domain;

import com.gestordetarefas.pessoa.application.api.*;
import com.gestordetarefas.tarefa.domain.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@NoArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    @Getter
    private UUID identificador;
    @Column (columnDefinition = "text")
    private String nome;
    @Column
    @Enumerated(EnumType.STRING)
    private Departamento departamento;
    @OneToMany(mappedBy = "pessoaAlocada")
    private List<Tarefa> tarefas;

    public Pessoa(NovaPessoaRequest novaPessoaDTO) {
        identificador = UUID.randomUUID();
        nome = novaPessoaDTO.nome();
        departamento = Departamento.valueOf(novaPessoaDTO.departamento().toUpperCase());
    }
}