package com.bytmasoft.dss.repository;


import com.bytmasoft.dss.entities.Course;
import org.springframework.data.jpa.domain.Specification;

public class CoursSpecification {

    public static Specification<Course> hasName(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isEmpty()) {
                return cb.conjunction();
            }
            return   cb.equal(root.get("name"), name);
        };
    }

    public static Specification<Course> hasDescription(String description) {
        return (root, query, cb) -> {
            if (description == null || description.isEmpty()) {
                return cb.conjunction();
            }
            return   cb.equal(root.get("description"), description);
        };
    }





    public static Specification<Course> hasActive(boolean isActive) {
        return (root, query, cb) -> {
            return   cb.equal(root.get("isActive"), isActive);
        };
    }
}
