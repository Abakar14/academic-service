package com.bytmasoft.dss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseUpdateDTO {

    private Long schoolId;
    private String name;
    private String description;
    private List<Long> documentIds;
    private Long teacherId;


}
