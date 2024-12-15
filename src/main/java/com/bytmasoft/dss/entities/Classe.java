package com.bytmasoft.dss.entities;

import com.bytmasoft.common.entities.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "classes")
public class Classe extends BaseEntity implements Serializable {

private static final long serialVersionUID = 1L;
@Column(nullable = false, unique = true)
@Size(min = 2, max = 100)
private String name;

private String description;

private String icon;

private int year;

private String semster;

private Long schoolId;

@ElementCollection
@CollectionTable(name = "student_classe", joinColumns = @JoinColumn(name = "student_id"))
@Column(name = "classe_id")
private List<Long> studentIds;

}
