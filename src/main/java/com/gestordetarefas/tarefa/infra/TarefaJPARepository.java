package com.gestordetarefas.tarefa.infra;

import com.gestordetarefas.tarefa.domain.*;
import org.springframework.data.jpa.repository.*;

public interface TarefaJPARepository extends JpaRepository<Tarefa, Long> {
}
