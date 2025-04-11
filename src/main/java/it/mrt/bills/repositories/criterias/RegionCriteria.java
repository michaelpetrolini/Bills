package it.mrt.bills.repositories.criterias;

import it.mrt.bills.entities.Region;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class RegionCriteria {

    public static Specification<Region> filterByName(Region.Name name) {
        return (Root<Region> root, CriteriaQuery<?> _, CriteriaBuilder cb) -> {
            if (name != null) {
                return cb.equal(root.get("name"), name);
            }

            return cb.conjunction();
        };
    }

    public static Specification<Region> filterByNames(List<String> names) {
        return (Root<Region> root, CriteriaQuery<?> _, CriteriaBuilder cb) -> {
            if (names != null) {
                return cb.or(names.stream().map(p -> cb.equal(root.get("name"), Region.Name.valueOf(p))).toList().toArray(new Predicate[0]));
            }

            return cb.conjunction();
        };
    }
}
