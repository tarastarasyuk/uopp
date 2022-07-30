package com.education.uopp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "education_info")
public class EducationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @OneToOne(mappedBy = "educationInfo")
    private Student student;

    @Enumerated(EnumType.STRING)
    @Column(name = "gained_degree")
    private Degree gainedDegree;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_degree")
    private Degree currentDegree;

    @Enumerated(EnumType.STRING)
    @Column(name = "wanted_degree")
    private Degree wantedDegree;
}
