package com.bytmasoft.dss.entities;

import com.bytmasoft.common.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course extends BaseEntity implements Serializable {

    @Column(nullable = false)
    @Size(min = 3, max = 50)
    private String name;

    @Column(nullable = false)
    @Size(min = 3, max = 250)
    private String description;

    @Column(nullable = false)
    @Size(min = 3, max = 50)
    private String code;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;


@Column(nullable = false)
    private Long schoolId;

     @Column(columnDefinition = "Boolean default false")
    private boolean deleted = false;

    @ElementCollection
    @CollectionTable(name = "course_document", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "document_id")
    private List<Long> documentIds;

@ElementCollection
@CollectionTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"))
@Column(name = "course_id")
private List<Long> studentIds;

}
