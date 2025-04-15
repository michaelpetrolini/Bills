package it.mrt.bills.repositories.criterias.gas;

import it.mrt.bills.dtos.filters.GasConsumptionFilters;
import it.mrt.bills.entities.gas.GasBill;
import it.mrt.bills.entities.gas.GasConsumption;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GasConsumptionCriteria {

    public static Specification<GasConsumption> filter(GasConsumptionFilters filters) {
        return (Root<GasConsumption> root, CriteriaQuery<?> _, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters.getBillId() != null) {
                Join<GasConsumption, GasBill> billJoin = root.join("gasBill", JoinType.LEFT);
                predicates.add(cb.equal(billJoin.get("id"), filters.getBillId()));
            }

            if (filters.getReferenceStartDate() != null) {
                predicates.add(cb.equal(root.get("referenceStartDate"), filters.getReferenceStartDate()));
            }

            if (filters.getReferenceEndDate() != null) {
                predicates.add(cb.equal(root.get("referenceEndDate"), filters.getReferenceEndDate()));
            }

            if (filters.getReadType() != null) {
                predicates.add(cb.equal(root.get("readType"), GasConsumption.ReadType.valueOf(filters.getReadType())));
            }

            if (predicates.isEmpty()) {
                return cb.conjunction();
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
