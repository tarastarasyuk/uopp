package com.education.uopp.springcourse.dto;

import com.education.uopp.springcourse.model.SCSkill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillDto {
    private String type;

    public SCSkill toSkill() {
        return new SCSkill(type);
    }
}
