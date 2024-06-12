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
    @Column (columnDefinition = "text")
    private String nome;
    @Column
    @Enumerated(EnumType.STRING)
    private Departamento departamento;
    @OneToMany(mappedBy = "pessoaAlocada")
    private List<Tarefa> tarefas;

    public Pessoa(PessoaRequest novaPessoaDTO) {
        identificador = UUID.randomUUID();
        nome = novaPessoaDTO.nome();
        departamento = Departamento.valueOf(novaPessoaDTO.departamento().toUpperCase());
    }

    public void atualiza(PessoaRequest pessoaRequest) {
        Departamento novoDepartamento = Departamento.valueOf(pessoaRequest.departamento().toUpperCase());;
        if (nome.equals(pessoaRequest.nome()) && departamento.equals(novoDepartamento))
            throw new APIException("Dados idênticos aos já existentes!", HttpStatus.CONFLICT);
        nome = pessoaRequest.nome();
        departamento = novoDepartamento;
    }
}