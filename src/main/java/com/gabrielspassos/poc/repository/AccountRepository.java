package com.gabrielspassos.poc.repository;

import com.gabrielspassos.poc.model.dto.AccountParamDTO;
import com.gabrielspassos.poc.model.entity.AccountEntity;
import liquibase.pro.packaged.T;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    default Page<AccountEntity> search(AccountParamDTO accountParamDTO, Pageable page){
        return findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            addPredicateAnd(accountParamDTO.getBank(), predicates, criteriaBuilder, root.get("bank"));
            addPredicateAnd(accountParamDTO.getAgency(), predicates, criteriaBuilder, root.get("agency"));
            addPredicateAnd(accountParamDTO.getNumber(), predicates, criteriaBuilder, root.get("number"));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, page);
    }

    Page<AccountEntity> findAll(Specification<Page<AccountEntity>> pageSpecification, Pageable pageRequest);

    default void addPredicateAnd(Object fieldToAdd, List<Predicate> predicates, CriteriaBuilder criteriaBuilder, Path<T> pathOfField) {
        if(Objects.nonNull(fieldToAdd)) {
            predicates.add(criteriaBuilder.and(criteriaBuilder.equal(pathOfField, fieldToAdd)));
        }
    }
}
