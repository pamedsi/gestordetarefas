package com.gestordetarefas.departamento.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@NoArgsConstructor
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Column(nullable = false, unique = true)
    private UUID identificador;
    @Column(nullable = false, unique = true)
    @Getter
    private String nome;
    @Column(nullable = false)
    private boolean deletado;

    public Departamento(String nome) {
        this.identificador = UUID.randomUUID();
        this.nome = nome;
    }
}