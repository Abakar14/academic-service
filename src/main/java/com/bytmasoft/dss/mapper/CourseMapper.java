package com.bytmasoft.dss.mapper;

import com.bytmasoft.dss.dto.CourseCreateDTO;
import com.bytmasoft.dss.dto.CourseDTO;
import com.bytmasoft.dss.dto.CourseUpdateDTO;
import com.bytmasoft.dss.entities.Cours;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourseMapper {

    CourseDTO toCoursDto(Cours cours);

    Cours toCours(CourseCreateDTO courseCreateDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCours(CourseUpdateDTO courseUpdateDTO, @MappingTarget Cours cours);

}
