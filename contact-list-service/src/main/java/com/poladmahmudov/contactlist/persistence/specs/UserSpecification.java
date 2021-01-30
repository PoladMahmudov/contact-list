package com.poladmahmudov.contactlist.persistence.specs;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.poladmahmudov.contactlist.persistence.domain.UserPo;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@EqualsAndHashCode
@RequiredArgsConstructor(staticName = "of")
public class UserSpecification implements Specification<UserPo> {

    private final String nameCriteria;

    @Override
    public Predicate toPredicate(Root<UserPo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (nameCriteria.endsWith("*")) {
            return criteriaBuilder.like(
                    criteriaBuilder.upper(root.get("name")), nameCriteria.toUpperCase().replaceFirst("\\*$", "%")
            );
        }
        if (nameCriteria.startsWith("*")) {
            return criteriaBuilder.like(
                    criteriaBuilder.upper(root.get("name")), nameCriteria.toUpperCase().replaceFirst("^\\*", "%")
            );
        }
        return criteriaBuilder.like(
                criteriaBuilder.upper(root.get("name")), "%" + nameCriteria.toUpperCase() + "%"
        );
    }
}
