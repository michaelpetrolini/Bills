package it.mrt.bills.repositories.criterias;

import it.mrt.bills.dtos.filters.EnergyConsumptionFilters;
import it.mrt.bills.entities.energy.EnergyBill;
import it.mrt.bills.entities.energy.EnergyConsumption;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class EnergyConsumptionCriteria {

    public static Specification<EnergyConsumption> filter(EnergyConsumptionFilters filters) {
        return (Root<EnergyConsumption> root, CriteriaQuery<?> _, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters.getBillId() != null) {
                Join<EnergyConsumption, EnergyBill> billJoin = root.join("energyBill", JoinType.LEFT);
                predicates.add(cb.equal(billJoin.get("id"), filters.getBillId()));
            }

            if (filters.getReferenceStartDate() != null) {
                predicates.add(cb.equal(root.get("referenceStartDate"), filters.getReferenceStartDate()));
            }

            if (filters.getReferenceEndDate() != null) {
                predicates.add(cb.equal(root.get("referenceEndDate"), filters.getReferenceEndDate()));
            }

            if (filters.getReadType() != null) {
                predicates.add(cb.equal(root.get("readType"), EnergyConsumption.ReadType.valueOf(filters.getReadType())));
            }

            if (predicates.isEmpty()) {
                return cb.conjunction();
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
