package com.gestordetarefas.pessoa.domain;

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
    @Column
    @Enumerated(EnumType.STRING)
    @Getter
    private Departamento departamento;
    @Column
    private boolean deletada;
    @OneToMany(mappedBy = "pessoaAlocada")
    private List<Tarefa> tarefas;

    public Pessoa(PessoaRequest novaPessoaDTO) {
        identificador = UUID.randomUUID();
        nome = novaPessoaDTO.nome();
        deletada = false;
        departamento = Departamento.valueOf(novaPessoaDTO.departamento().toUpperCase());
    }

    public void atualiza(PessoaRequest pessoaRequest) {
        Departamento novoDepartamento = Departamento.valueOf(pessoaRequest.departamento().toUpperCase());
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
}