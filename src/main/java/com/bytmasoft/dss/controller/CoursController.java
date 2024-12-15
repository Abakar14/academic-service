package com.bytmasoft.dss.controller;


import com.bytmasoft.common.controller.DSSCrud;
import com.bytmasoft.common.exception.DSSEntityNotFoundException;
import com.bytmasoft.dss.dto.CourseCreateDTO;
import com.bytmasoft.dss.dto.CourseDTO;
import com.bytmasoft.dss.dto.CourseUpdateDTO;
import com.bytmasoft.dss.service.CourseService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Manage classes, courses, timetables, and exams.
 * Publish results and generate grade reports.
 * Track student performance across courses.
 */

@Schema(name ="Teacher-Service")
@Validated
@AllArgsConstructor
@RequestMapping(value = "/courses", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Course Service Api", description = "Course Service for course management")
@RestController
public class CoursController implements DSSCrud<CourseDTO, CourseCreateDTO, CourseUpdateDTO> {

    private final CourseService coursService;
private final PagedResourcesAssembler<CourseDTO> pagedResourcesAssembler;



    @Override
    public ResponseEntity<CourseDTO> save(@Valid CourseCreateDTO courseCreateDTO) {
      return    ResponseEntity.ok(coursService.add(courseCreateDTO));
    }


@GetMapping
public PagedModel<EntityModel<CourseDTO>> findAll(
        @RequestParam(value = "search", required = false) String search,
        @ParameterObject @Parameter(description = "Pagination information",required = false,schema = @Schema(
                implementation = Pageable.class
        )) @PageableDefault(page = 0,size = 10,direction = Sort.Direction.ASC) Pageable pageable)
 {
     Page<CourseDTO> page;
     if(search != null && !search.isEmpty()) {
         page = coursService.searchCourses(search, pageable);
     }else{
         page = coursService.findAll(pageable);
     }

     return pagedResourcesAssembler.toModel(page);
 }


    @Override
    public List<CourseDTO> findList() {
        return coursService.findAllAsList();
    }

    @Override
    public ResponseEntity<CourseDTO> unlock(Long id) throws DSSEntityNotFoundException {
      return ResponseEntity.ok(coursService.unlock(id));
    }

    @Override
    public ResponseEntity<CourseDTO> lockout(Long id) throws DSSEntityNotFoundException {
        return ResponseEntity.ok(coursService.lockout(id));
    }

    @Override
    public ResponseEntity<CourseDTO> markfordeletion(Long id) throws DSSEntityNotFoundException {
        return ResponseEntity.ok(coursService.markfordeletion(id));
    }

    @Override
    public Long countAll() {
        return coursService.countAll();
    }

    @Override
    public Long countAllActives() {
        return coursService.countAllActives();
    }

    @Override
    public Long countAllLocked() {
        return coursService.countAllLocked();
    }

    @Override
    public ResponseEntity<CourseDTO> findById(Long id) throws DSSEntityNotFoundException {
        return ResponseEntity.ok(coursService.findById(id));
    }

    @Override
    public ResponseEntity<CourseDTO> update(Long id, @Valid CourseUpdateDTO courseUpdateDTO) throws DSSEntityNotFoundException {
        return ResponseEntity.ok(coursService.update(id, courseUpdateDTO));
    }

    @Override
    public ResponseEntity<CourseDTO> delete(Long id) throws DSSEntityNotFoundException {
        return ResponseEntity.ok(coursService.delete(id));
    }
}
