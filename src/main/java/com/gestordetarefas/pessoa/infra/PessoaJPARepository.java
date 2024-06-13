package com.gestordetarefas.pessoa.infra;

import com.gestordetarefas.pessoa.domain.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface PessoaJPARepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByIdentificadorAndDeletadaFalse(UUID uuid);
    Page<Pessoa> findAllByDeletadaFalse(Pageable pageable);
}
