package com.bytmasoft.dss.dto;

import com.bytmasoft.dss.entities.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO {

    private Long id;
    private Long schoolId;
    private String name;
    private String description;
    private List<Long> documentIds;
    private boolean deleted;
    private LocalDateTime addedOn;
    private LocalDateTime modifiedOn;
    private Teacher teacher;
    private boolean active;
    private String updatedBy;
    private String addedBy;
    private String modifiedBy;

}
