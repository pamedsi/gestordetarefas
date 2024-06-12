package com.gestordetarefas.pessoa.infra;

import com.gestordetarefas.pessoa.domain.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface PessoaJPARepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByIdentificador(UUID uuid);
}
