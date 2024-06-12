package com.gestordetarefas.pessoa.infra;

import com.gestordetarefas.pessoa.domain.*;
import org.springframework.data.jpa.repository.*;

public interface PessoaJPARepository extends JpaRepository<Pessoa, Long> {

}
