package it.mrt.bills.repositories.criterias;

import it.mrt.bills.entities.CommonParameter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class CommonParameterCriteria {

    public static Specification<CommonParameter> filterByName(CommonParameter.ParameterType name) {
        return (Root<CommonParameter> root, CriteriaQuery<?> _, CriteriaBuilder cb) -> {
            if (name != null) {
                return cb.equal(root.get("name"), name);
            }

            return cb.conjunction();
        };
    }

    public static Specification<CommonParameter> filterByNames(List<String> parameterTypes) {
        return (Root<CommonParameter> root, CriteriaQuery<?> _, CriteriaBuilder cb) -> {
            if (parameterTypes != null) {
                return cb.or(parameterTypes.stream().map(p -> cb.equal(root.get("name"), CommonParameter.ParameterType.valueOf(p))).toList().toArray(new Predicate[0]));
            }

            return cb.conjunction();
        };
    }
}
