package com.bytmasoft.dss.repository;

import com.bytmasoft.dss.entities.Cours;
import org.springframework.data.jpa.domain.Specification;

public class CoursSpecification {

    public static Specification<Cours> hasName(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isEmpty()) {
                return cb.conjunction();
            }
            return   cb.equal(root.get("name"), name);
        };
    }

    public static Specification<Cours> hasDescription(String description) {
        return (root, query, cb) -> {
            if (description == null || description.isEmpty()) {
                return cb.conjunction();
            }
            return   cb.equal(root.get("description"), description);
        };
    }





    public static Specification<Cours> hasActive(boolean isActive) {
        return (root, query, cb) -> {
            return   cb.equal(root.get("isActive"), isActive);
        };
    }
}
