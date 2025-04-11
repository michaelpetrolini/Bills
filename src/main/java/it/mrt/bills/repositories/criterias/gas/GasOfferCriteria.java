package it.mrt.bills.repositories.criterias.gas;

import it.mrt.bills.dtos.filters.GasOfferFilters;
import it.mrt.bills.entities.gas.GasOffer;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GasOfferCriteria {

    public static Specification<GasOffer> filter(GasOfferFilters filters) {
        return (Root<GasOffer> root, CriteriaQuery<?> _, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters.getCode() != null) {
                predicates.add(cb.equal(root.get("code"), filters.getCode()));
            }

            if (predicates.isEmpty()) {
                return cb.conjunction();
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
