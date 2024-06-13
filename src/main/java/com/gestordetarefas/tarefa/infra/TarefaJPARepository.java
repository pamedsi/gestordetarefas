package com.gestordetarefas.tarefa.infra;

import com.gestordetarefas.tarefa.domain.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface TarefaJPARepository extends JpaRepository<Tarefa, Long> {
    Optional<Tarefa> findByIdentificadorAndDeletadaFalse(UUID identificador);
}
