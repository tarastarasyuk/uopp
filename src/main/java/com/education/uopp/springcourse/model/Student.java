package com.education.uopp.springcourse.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"}, callSuper = true)
@NoArgsConstructor
public class Student extends User {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String phone;
    private Set<Skill> skills;
    private Set<Opportunity> likedOpportunities = new HashSet<>();

    public Student(
            Long userId, String email,
            Long studentId, String firstName, String lastName, Integer age, String phone,
            Set<Skill> skills, Set<Opportunity> likedOpportunities
    ) {
        super(userId, email, Role.ROLE_STUDENT);
        this.id = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.skills = skills;
        this.likedOpportunities = likedOpportunities;
    }

    public Student(
            String email,
            String firstName, String lastName, Integer age, String phone,
            Set<Skill> skills
    ) {
        super(email, Role.ROLE_STUDENT);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Student: {%s, id: %d, firstName: %s, lastName: %s, age: %d, phone: %s, skills: %s, likedOpportunities: %s}"
                .formatted(super.toString(), id, firstName, lastName, age, phone, skills, likedOpportunities);
    }
}
