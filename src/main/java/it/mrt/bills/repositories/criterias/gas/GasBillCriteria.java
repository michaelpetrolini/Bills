package it.mrt.bills.repositories.criterias.gas;

import it.mrt.bills.dtos.filters.GasBillFilters;
import it.mrt.bills.entities.gas.GasBill;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GasBillCriteria {

    public static Specification<GasBill> filter(GasBillFilters filters) {
        return (Root<GasBill> root, CriteriaQuery<?> _, CriteriaBuilder cb) -> {
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
