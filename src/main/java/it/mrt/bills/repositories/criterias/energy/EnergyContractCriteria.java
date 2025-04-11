package it.mrt.bills.repositories.criterias.energy;

import it.mrt.bills.dtos.filters.EnergyContractFilters;
import it.mrt.bills.entities.energy.EnergyContract;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class EnergyContractCriteria {

    public static Specification<EnergyContract> filter(EnergyContractFilters filters) {
        return (Root<EnergyContract> root, CriteriaQuery<?> _, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters.getPodCode() != null) {
                predicates.add(cb.equal(root.get("podCode"), filters.getPodCode()));
            }

            if (predicates.isEmpty()) {
                return cb.conjunction();
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
