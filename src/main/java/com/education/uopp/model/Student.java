package com.education.uopp.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity(name = "Student")
@Table(name = "student")
public class Student extends User {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "age")
    private Integer age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "education_info_id", referencedColumnName = "id")
    private EducationInfo educationInfo;

    @Column(name = "phone")
    private Long phone;

}
