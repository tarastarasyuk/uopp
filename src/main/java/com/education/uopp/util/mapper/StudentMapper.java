package com.education.uopp.util.mapper;

import com.education.uopp.domain.entity.Student;
import com.education.uopp.domain.model.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
    @Mapping(target = "role", source="role",
            defaultValue = "ROLE_STUDENT")
    Student studentDTOtoStudent (StudentDTO studentDTO);
    StudentDTO studentToStudentDTO (Student student);
}
