package com.bytmasoft.dss.controller;


import com.bytmasoft.common.exception.DSSEntityNotFoundException;
import com.bytmasoft.dss.dto.*;
import com.bytmasoft.dss.entities.Exam;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Manage classes, courses, timetables, and exams.
 * Publish results and generate grade reports.
 * Track student performance across courses.
 */

@Schema(name = "Teacher-Service")
@Validated
@AllArgsConstructor
@RequestMapping(value = "/academic", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Academic Service Api", description = "Academic Service for academics management")
@RestController
public class AcademicController {

private final CourseService courseService;
private final PagedResourcesAssembler<CourseDTO> pagedResourcesAssembler;


@PostMapping("/courses")
public ResponseEntity<CourseDTO> save(@RequestBody @Valid CourseCreateDTO courseCreateDTO) {
	return ResponseEntity.ok(courseService.add(courseCreateDTO));
}

@PostMapping("/courses/{course_id}/students")
ResponseEntity<CourseDTO> enrollStudentsToCourse(@PathVariable Long course_id, @RequestBody List<Long> studentIds) {
    return ResponseEntity.ok(courseService.enrollStudentsToCourse(course_id, studentIds));
}

@GetMapping({"courses/{id}"})
public ResponseEntity<CourseDTO> findById(Long id) throws DSSEntityNotFoundException {
	return ResponseEntity.ok(courseService.findById(id));
}
@GetMapping("/courses/{id}/students")
public ResponseEntity<List<Long>> fetchStudentIdsInCourse(@PathVariable Long id) {
    return ResponseEntity.ok(courseService.fetchStudentIdsInCourse(id));
}

@GetMapping("/courses")
public PagedModel<EntityModel<CourseDTO>> findAll(
		@RequestParam(value = "search", required = false) String search,
		@ParameterObject @Parameter(description = "Pagination information", required = false, schema = @Schema(
				implementation = Pageable.class
		)) @PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable) {
	Page<CourseDTO> page;
	if (search != null && !search.isEmpty()) {
		page = courseService.searchCourses(search, pageable);
	} else {
		page = courseService.findAll(pageable);
	}

	return pagedResourcesAssembler.toModel(page);
}

@GetMapping({"/courses/list"})
public List<CourseDTO> findList() {
    return courseService.findAllAsList();
}

@GetMapping({"/courses/count"})
public Long countAll() {
    return courseService.countAll();
}

@GetMapping({"/courses/active/count"})
public Long countAllActives() {
    return courseService.countAllActives();
}

@GetMapping({"/courses/locked/count"})
public Long countAllLocked() {
    return courseService.countAllLocked();
}

@PutMapping({"/courses/{id}"})
public ResponseEntity<CourseDTO> update(Long id, @Valid CourseUpdateDTO courseUpdateDTO) throws DSSEntityNotFoundException {
    return ResponseEntity.ok(courseService.update(id, courseUpdateDTO));
}

@DeleteMapping({"/courses/{id}"})
public ResponseEntity<CourseDTO> delete(Long id) throws DSSEntityNotFoundException {
    return ResponseEntity.ok(courseService.delete(id));
}

@PutMapping({"/courses/{id}/unlock"})
public ResponseEntity<CourseDTO> unlock(@PathVariable Long id) throws DSSEntityNotFoundException {
	return ResponseEntity.ok(courseService.unlock(id));
}

@DeleteMapping({"/courses/{id}/lockout"})
public ResponseEntity<CourseDTO> lockout(@PathVariable  Long id) throws DSSEntityNotFoundException {
	return ResponseEntity.ok(courseService.lockout(id));
}

@DeleteMapping({"/courses/{id}/markfordeletion"})
public ResponseEntity<CourseDTO> markfordeletion(@PathVariable Long id) throws DSSEntityNotFoundException {
	return ResponseEntity.ok(courseService.markfordeletion(id));
}

/**
 * Submit exam results for students
 */
@PostMapping("/exams/results")
public ResponseEntity<ResultDto> results(@RequestBody @Valid ExamCreateDto examCreateDto) {
	return ResponseEntity.ok(null);
}

@GetMapping("/academic/exams/{exam_id}/restults")
public ResponseEntity<ResultDto> resultsForExam(@PathVariable Long exam_id) throws DSSEntityNotFoundException {
	return ResponseEntity.ok(null);
}

@GetMapping("/academic/timetables/{classe_Id}")
public ResponseEntity <TimetableDto> getTimetableForClass(@PathVariable Long classe_Id) {
	return ResponseEntity.ok(null);
}

@GetMapping("/academic/timetables/students/{student_id}")
public ResponseEntity <TimetableDto> getTimetableForStudentById(@PathVariable Long student_id) {
	return ResponseEntity.ok(null);
}




}
