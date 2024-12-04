package com.bytmasoft.dss.service;

import com.bytmasoft.common.exception.DSSEntityNotFoundException;
import com.bytmasoft.dss.dto.CourseCreateDTO;
import com.bytmasoft.dss.dto.CourseDTO;
import com.bytmasoft.dss.dto.CourseUpdateDTO;
import com.bytmasoft.dss.entities.Cours;
import com.bytmasoft.dss.mapper.CourseMapper;
import com.bytmasoft.dss.repository.CoursRepository;
import com.bytmasoft.dss.repository.CoursSpecification;
import com.bytmasoft.dss.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CoursService {
        //implements ServiceApi<CourseDTO, CourseCreateDTO, CourseUpdateDTO> {
    private static final Logger logger = LoggerFactory.getLogger(CoursService.class);

    private final CoursRepository coursRepository;
    private final PagedResourcesAssembler<CourseDTO> pagedResourcesAssembler;
    private final CourseMapper courseMapper;
    private final ApiUtils apiUtils;

    public CourseDTO add(CourseCreateDTO courseCreateDTO) {
        Cours cours = courseMapper.toCours(courseCreateDTO);
        cours.setAddedBy(apiUtils.getUsername());
        Cours savedCours = coursRepository.save(cours);
        logger.info("User {} successfully Created student with id  {}", apiUtils.getUsername() , savedCours.getId());

        return courseMapper.toCoursDto(savedCours);

    }

    private Cours getCours(Long id){
        return coursRepository.findById(id).orElseThrow(()-> new DSSEntityNotFoundException("Cours with id: "+id+" not found"));

    }

    public Page<CourseDTO> findAll(Pageable pageable) {
        Page<Cours> coursPage = coursRepository.findAll(pageable);

        return coursPage.map(courseMapper::toCoursDto);
    }

public Page<CourseDTO> searchCourses(String search, Pageable pageable) {
    return coursRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(search, search, pageable)
                   .map(courseMapper::toCoursDto);
    }

    public Page<CourseDTO> findAllByActiveStatus(boolean active, Pageable pageable) {
        return coursRepository.findAll(CoursSpecification.hasActive(active), pageable).map(courseMapper::toCoursDto);
    }

    public List<CourseDTO> findAllAsList() {
        return coursRepository.findAll().stream().map(courseMapper::toCoursDto).collect(Collectors.toList());

    }

    public CourseDTO findById(Long id) throws DSSEntityNotFoundException {
        return courseMapper.toCoursDto(getCours(id));
    }

    public CourseDTO update(Long aLong, CourseUpdateDTO courseUpdateDTO) {
     Cours cours = getCours(aLong);
     courseMapper.updateCours(courseUpdateDTO, cours);
     cours.setModifiedBy(apiUtils.getUsername());
        logger.info("User {} successfully update cours with id  {}", apiUtils.getUsername() , cours.getId());
        Cours savedCours = coursRepository.save(cours);
        return courseMapper.toCoursDto(savedCours);
    }

    public CourseDTO delete(Long id) {
        Cours cours = getCours(id);
        cours.setModifiedBy(apiUtils.getUsername());
        coursRepository.delete(cours);
        return courseMapper.toCoursDto(cours);

    }

    public CourseDTO unlock(Long id) {
        Cours cours = getCours(id);
        cours.setIsActive(true);
        cours.setModifiedBy(apiUtils.getUsername());
        return courseMapper.toCoursDto(cours);
    }

    public Long countAllLocked() {
        return coursRepository.count(CoursSpecification.hasActive(false));
    }

    public Long countAllActives() {
        return coursRepository.count(CoursSpecification.hasActive(true));
    }

    public Long countAll() {
        return coursRepository.count();
    }

    public CourseDTO markfordeletion(Long id) {
        Cours cours = getCours(id);
        cours.setDeleted(true);
        cours.setModifiedBy(apiUtils.getUsername());
        coursRepository.save(cours);
        return courseMapper.toCoursDto(cours);
    }

    public CourseDTO lockout(Long id) {
        Cours cours = getCours(id);
        cours.setIsActive(false);
        cours.setModifiedBy(apiUtils.getUsername());
        coursRepository.save(cours);
        return courseMapper.toCoursDto(cours);
    }


}
