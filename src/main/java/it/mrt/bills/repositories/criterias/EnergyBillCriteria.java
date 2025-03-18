package it.mrt.bills.repositories.criterias;

import it.mrt.bills.dtos.filters.EnergyBillFilters;
import it.mrt.bills.entities.energy.EnergyBill;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class EnergyBillCriteria {

    public static Specification<EnergyBill> filter(EnergyBillFilters filters) {
        return (Root<EnergyBill> root, CriteriaQuery<?> _, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters.getInvoiceNumber() != null) {
                predicates.add(cb.equal(root.get("invoiceNumber"), filters.getInvoiceNumber()));
            }

            if (predicates.isEmpty()) {
                return cb.conjunction();
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
