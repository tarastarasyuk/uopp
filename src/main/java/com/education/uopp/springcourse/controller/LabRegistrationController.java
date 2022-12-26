package com.education.uopp.springcourse.controller;

import com.education.uopp.springcourse.dto.SignInDto;
import com.education.uopp.springcourse.dto.StudentDto;
import com.education.uopp.springcourse.model.SCSkill;
import com.education.uopp.springcourse.model.SCStudent;
import com.education.uopp.springcourse.model.SCUser;
import com.education.uopp.springcourse.service.EditorService;
import com.education.uopp.springcourse.service.SkillService;
import com.education.uopp.springcourse.service.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class LabRegistrationController {

    private final SkillService skillService;
    private final StudentService studentService;
    private final EditorService editorService;

    @ApiOperation(value = "Sign up student", notes = "Calling this endpoint you will register new student")
    @PostMapping("/sign-up")
    public ResponseEntity<SCStudent> signUpStudent(
            @ApiParam(name = "studentDto", required = true, type = "StudentDto", value = "Student information")
            @RequestBody StudentDto studentDto) {
        if (studentService.existsByEmail(studentDto.getEmail()) || editorService.existsByEmail(studentDto.getEmail())) {
            return new ResponseEntity<>(HttpStatus.IM_USED);
        }

        Set<SCSkill> skillSet = Arrays.stream(studentDto.getSkills())
                .map(skillService::findByType)
                .collect(Collectors.toSet());

        return new ResponseEntity<>(studentService.create(studentDto.toStudent(skillSet)), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Sign in student", notes = "Calling this endpoint you will get a user, exception otherwise")
    @PostMapping("/sign-in")
    public ResponseEntity<SCUser> signInStudent(
            @ApiParam(name = "signInDto", readOnly = true, type = "SignInDto", value = "Sign in information")
            @RequestBody SignInDto signInDto) {
        String email = signInDto.getEmail();
        if (studentService.existsByEmail(email)) {
            return new ResponseEntity<>(studentService.findByEmail(email), HttpStatus.OK);
        }
        if (editorService.existsByEmail(email)) {
            return new ResponseEntity<>(editorService.findByEmail(email), HttpStatus.OK);
        }
        throw new RuntimeException("Invalid email");
    }
}
