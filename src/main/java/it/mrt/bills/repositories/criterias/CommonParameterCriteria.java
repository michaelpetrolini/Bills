package it.mrt.bills.repositories.criterias;

import it.mrt.bills.entities.CommonParameter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class CommonParameterCriteria {

    public static Specification<CommonParameter> filter(String name) {
        return (Root<CommonParameter> root, CriteriaQuery<?> _, CriteriaBuilder cb) -> {
            if (name != null) {
                return cb.equal(root.get("name"), name);
            }

            return cb.conjunction();
        };
    }
}
