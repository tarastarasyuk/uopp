package com.education.uopp.mapper;

import com.education.uopp.entity.Role;
import com.education.uopp.entity.Student;
import com.education.uopp.model.StudentDTO;
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
