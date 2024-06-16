package com.gestordetarefas.pessoa.domain;

import com.gestordetarefas.departamento.domain.*;
import com.gestordetarefas.exception.*;
import com.gestordetarefas.pessoa.application.api.*;
import com.gestordetarefas.tarefa.domain.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.*;

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
    @Getter
    @Column (columnDefinition = "TEXT")
    private String nome;
    @Getter
    @ManyToOne
    private Departamento departamento;
    @Column
    private boolean deletada;
    @OneToMany(mappedBy = "pessoaAlocada")
    private List<Tarefa> tarefas;

    public Pessoa(PessoaRequest novaPessoaDTO, Departamento departamento) {
        identificador = UUID.randomUUID();
        nome = novaPessoaDTO.nome();
        this.departamento = departamento;
        deletada = false;
    }

    public void atualiza(PessoaRequest pessoaRequest, Departamento novoDepartamento) {
        if (nome.equals(pessoaRequest.nome()) && departamento.equals(novoDepartamento))
            throw new APIException("Dados idênticos aos já existentes!", HttpStatus.CONFLICT);
        nome = pessoaRequest.nome();
        departamento = novoDepartamento;
    }

    public void deleta() {
        deletada = true;
    }

    public int getHorasTrabalhadas() {
        return (int) tarefas.stream()
                .filter(Tarefa::estaFinalizada)
                .mapToLong(Tarefa::getDuracaoEmHoras)
                .sum();
    }

    public int getMediaDeHorasTrabalhadas() {
        List<Tarefa> tarefasConcluidas = tarefas.stream().filter(Tarefa::estaFinalizada).toList();
        if (tarefasConcluidas.isEmpty()) return 0;
        return ((int) tarefasConcluidas.stream().mapToLong(Tarefa::getDuracaoEmHoras).sum()) / tarefasConcluidas.size();
    }
}