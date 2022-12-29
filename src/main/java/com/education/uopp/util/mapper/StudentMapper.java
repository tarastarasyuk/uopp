package com.education.uopp.util.mapper;

import com.education.uopp.domain.entity.Student;
import com.education.uopp.domain.model.StudentDTO;
import com.education.uopp.springcourse.model.SCSkill;
import com.education.uopp.springcourse.service.SkillService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {

    @Autowired
    private SkillService skillService;

    @Mapping(target = "role", source = "role",
            defaultValue = "ROLE_STUDENT")
    @Mapping(target = "skills", source = "skills", qualifiedByName = "mapSkills")
    public abstract Student studentDTOtoStudent(StudentDTO studentDTO);

    @Mapping(target = "skills", source = "skills", qualifiedByName = "mapSkillsToString")
    public abstract StudentDTO studentToStudentDTO(Student student);

    @Named("mapSkills")
    Set<SCSkill> mapSkills(String[] skills) {
        if (Objects.isNull(skills)) {
            return Collections.emptySet();
        }
        return stream(skills)
                .map(skillService::findByType)
                .collect(Collectors.toSet());
    }

    @Named("mapSkillsToString")
    String[] mapSkillsToString(Set<SCSkill> skills) {
        return skills.stream()
                .map(SCSkill::toString)
                .toArray(String[]::new);
    }

}
