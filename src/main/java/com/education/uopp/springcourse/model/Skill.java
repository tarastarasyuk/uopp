package com.education.uopp.springcourse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
    private Long id;
    private String type;

    public Skill(String type) {
        this.type = type;
    }
}
