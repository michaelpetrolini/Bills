package it.mrt.bills.repositories.criterias.gas;

import it.mrt.bills.dtos.filters.GasContractFilters;
import it.mrt.bills.entities.gas.GasContract;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GasContractCriteria {

    public static Specification<GasContract> filter(GasContractFilters filters) {
        return (Root<GasContract> root, CriteriaQuery<?> _, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters.getPdrCode() != null) {
                predicates.add(cb.equal(root.get("pdrCode"), filters.getPdrCode()));
            }

            if (predicates.isEmpty()) {
                return cb.conjunction();
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
