package it.mrt.bills.repositories.criterias;

import it.mrt.bills.dtos.filters.UserFilters;
import it.mrt.bills.entities.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserCriteria {

    public static Specification<User> filter(UserFilters filters) {
        return (Root<User> root, CriteriaQuery<?> _, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters.getTaxCode() != null) {
                predicates.add(cb.equal(root.get("taxCode"), filters.getTaxCode()));
            }

            if (predicates.isEmpty()) {
                return cb.conjunction();
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<User> byEmail(String email) {
        return (Root<User> root, CriteriaQuery<?> _, CriteriaBuilder cb) -> cb.equal(root.get("email"), email);
    }
}
