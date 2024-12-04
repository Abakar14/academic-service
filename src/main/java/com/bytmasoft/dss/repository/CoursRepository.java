package com.bytmasoft.dss.repository;

import com.bytmasoft.dss.entities.Cours;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CoursRepository extends JpaRepository<Cours, Long>, JpaSpecificationExecutor<Cours> {
Page<Cours> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String search, String search1, Pageable pageable);
}
