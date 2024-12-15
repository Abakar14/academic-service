package com.bytmasoft.dss.repository;

import com.bytmasoft.dss.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CoursRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {
Page<Course> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String search, String search1, Pageable pageable);
}
