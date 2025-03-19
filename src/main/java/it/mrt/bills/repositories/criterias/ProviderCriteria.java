package it.mrt.bills.repositories.criterias;

import it.mrt.bills.dtos.filters.ProviderFilters;
import it.mrt.bills.entities.Provider;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProviderCriteria {

    public static Specification<Provider> filter(ProviderFilters filters) {
        return (Root<Provider> root, CriteriaQuery<?> _, CriteriaBuilder cb) -> {
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
}
