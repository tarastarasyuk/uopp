package com.education.uopp.springcourse.dto;

import com.education.uopp.springcourse.model.SCSkill;
import com.education.uopp.springcourse.model.SCStudent;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class StudentDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Integer age;
    private String[] skills;

    public SCStudent toStudent(Set<SCSkill> skillSet) {
        return new SCStudent(email, firstName, lastName, age, phone, skillSet);
    }
}
