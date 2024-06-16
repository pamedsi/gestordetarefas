package com.gestordetarefas.pessoa.infra;

import com.gestordetarefas.departamento.domain.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface DepartamentoJPARepository extends JpaRepository<Departamento, Long> {
    Optional<Departamento> findByIdentificador(UUID identificadorDoDepartamento);
    Page<Departamento> findAllByDeletadoFalse(Pageable pageable);
}