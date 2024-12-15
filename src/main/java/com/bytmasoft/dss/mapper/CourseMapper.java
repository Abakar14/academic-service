package com.bytmasoft.dss.mapper;

import com.bytmasoft.dss.dto.CourseCreateDTO;
import com.bytmasoft.dss.dto.CourseDTO;
import com.bytmasoft.dss.dto.CourseUpdateDTO;
import com.bytmasoft.dss.entities.Course;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourseMapper {

    CourseDTO toCoursDto(Course cours);

    Course toCours(CourseCreateDTO courseCreateDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCours(CourseUpdateDTO courseUpdateDTO, @MappingTarget Course cours);

}
