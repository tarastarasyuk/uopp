package com.education.uopp.springcourse.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "sc_student")
public class SCStudent extends SCUser {
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String phone;

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

    public SCStudent(
            Long userId, String email, SCRole role,
            String firstName, String lastName, Integer age, String phone,
            Set<SCSkill> skills, Set<SCOpportunity> likedOpportunities
    ) {
        super(userId, email, role);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.skills = skills;
        this.likedOpportunities = likedOpportunities;
    }

    public SCStudent(
            String email,
            String firstName, String lastName, Integer age, String phone,
            Set<SCSkill> skills
    ) {
        super(email, SCRole.ROLE_STUDENT);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Student: { %s, firstName: %s, lastName: %s, age: %d, phone: %s, skills: %s, likedOpportunities: %s }"
                .formatted(super.toString(), firstName, lastName, age, phone, skills, likedOpportunities);
    }
}
