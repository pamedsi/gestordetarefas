package com.gestordetarefas.pessoa.infra;

import com.gestordetarefas.departamento.domain.*;
import com.gestordetarefas.exception.*;
import com.gestordetarefas.pessoa.application.repository.*;
import com.gestordetarefas.pessoa.domain.*;
import jakarta.annotation.*;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import lombok.*;
import lombok.extern.log4j.*;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
@RequiredArgsConstructor
@Log4j2
public class PessoaInfraRepository implements PessoaRepository {
    private final PessoaJPARepository pessoaJPARepository;
    private final EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    @PostConstruct
    private void instanciarCriteriaBuilder() { this.criteriaBuilder = entityManager.getCriteriaBuilder(); }

    @Override
    public void salvaNovaPessoa(Pessoa pessoa) {
        log.info("[inicia]  PessoaInfraRepository - salvarNovaPessoa");
        try {
            pessoaJPARepository.save(pessoa);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            throw new APIException("Ocorreu um erro. Contate o suporte!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("[finaliza]  PessoaInfraRepository - salvarNovaPessoa");
    }

    @Override
    public Pessoa buscaPessoaPorIdentificador(String identificador) {
        log.info("[inicia]  PessoaInfraRepository - buscaPessoaPorIdentificador");
        Pessoa pessoa = pessoaJPARepository.findByIdentificadorAndDeletadaFalse(UUID.fromString(identificador)).orElseThrow(
                () -> new APIException("Pessoa não encontrada.", HttpStatus.NOT_FOUND)
        );
        log.info("[finaliza]  PessoaInfraRepository - buscaPessoaPorIdentificador");
        return pessoa;
    }

    @Override
    public Page<Pessoa> buscaPessoas(Pageable pageable) {
        log.info("[inicia]  PessoaInfraRepository - buscaPessoas");
        Page<Pessoa> pessoas = pessoaJPARepository.findAllByDeletadaFalse(pageable);
        log.info("[finaliza]  PessoaInfraRepository - buscaPessoas");
        return pessoas;
    }

    @Override
    public Page<Pessoa> buscaPessoasPorNome(String nome, Pageable pageable) {
        log.info("[inicia]  PessoaInfraRepository - buscaPessoasPorNome");
        CriteriaQuery<Pessoa> criteriaQuery = criteriaBuilder.createQuery(Pessoa.class);
        Root<Pessoa> root = criteriaQuery.from(Pessoa.class);
        Predicate queryPredicate = criaPredicate(root, nome);

        criteriaQuery.where(queryPredicate);
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("nome")));
        TypedQuery<Pessoa> query = entityManager.createQuery(criteriaQuery);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        Long productsCount = contaPessoas(nome);
        Page<Pessoa> resultado = new PageImpl<>(query.getResultList(), pageable, productsCount);
        log.info("[finaliza]  PessoaInfraRepository - buscaPessoasPorNome");
        return resultado;
    }

    @Override
    public int contaPessoasPorDepartamento(Departamento departamento) {
        log.info("[inicia]  PessoaInfraRepository - contaPessoasPorDepartamento");
        int quantidadeDePessoas = pessoaJPARepository.countByDepartamento(departamento);
        log.info("[finaliza]  PessoaInfraRepository - contaPessoasPorDepartamento");
        return quantidadeDePessoas;
    }

    private Predicate criaPredicate(Root<Pessoa> root, String nome){
        Predicate tituloPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
        Predicate orPredicate = criteriaBuilder.or(tituloPredicate);
        Predicate deletedPredicate = criteriaBuilder.equal(root.get("deletada"), false);
        return criteriaBuilder.and(orPredicate, deletedPredicate);
    }

    private Long contaPessoas(String nome) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Pessoa> countRoot = countQuery.from(Pessoa.class);
        Predicate predicate = criaPredicate(countRoot, nome);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
