package com.gestordetarefas.tarefa.domain;

import com.gestordetarefas.exception.*;
import com.gestordetarefas.pessoa.domain.*;
import com.gestordetarefas.tarefa.application.api.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.*;

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
    @Getter
    @Column
    private int duracaoEmHoras;
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoaAlocada;
    @Column
    private boolean finalizada;
    @Column
    private boolean deletada;

    public Tarefa(CriarTarefaRequest tarefaDTO) {
        identificador = UUID.randomUUID();
        titulo = tarefaDTO.titulo();
        descricao = tarefaDTO.descricao();
        prazo = tarefaDTO.prazo();
        departamento = Departamento.valueOf(tarefaDTO.departamento());
        duracaoEmHoras = tarefaDTO.duracao();
        finalizada = false;
        deletada = false;
    }

    public void deleta() {
        deletada = true;
    }

    public void aloca(Pessoa pessoa) {
        if (!pessoa.getDepartamento().equals(departamento))
            throw new APIException("Esta pessoa não é do mesmo departamento que esta tarefa.", HttpStatus.CONFLICT);
        pessoaAlocada = pessoa;
    }

    public void finaliza() {
        finalizada = true;
    }

    public boolean estaFinalizada() {
        return finalizada;
    }
}
