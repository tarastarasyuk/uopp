package com.education.uopp.domain.entity;

import com.education.uopp.springcourse.model.SCOpportunity;
import com.education.uopp.springcourse.model.SCSkill;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "sc_student")
public class Student extends User {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    private LocalDate birthday;

    private Integer age;

    private Long phone;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "student_skills",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<SCSkill> skills;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "student_liked_opportunities",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "opportunity_id"))
    private Set<SCOpportunity> likedOpportunities = new HashSet<>();

    @Override
    public String toString() {
        return "Student: { %s, firstName: %s, lastName: %s, age: %d, phone: %s, skills: %s, likedOpportunities: %s }"
                .formatted(super.toString(), firstName, lastName, age, phone, skills, likedOpportunities);
    }

}
