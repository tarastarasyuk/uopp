package com.education.uopp.springcourse.controller;

import com.education.uopp.springcourse.dto.StudentDto;
import com.education.uopp.springcourse.model.Skill;
import com.education.uopp.springcourse.model.Student;
import com.education.uopp.springcourse.service.EditorService;
import com.education.uopp.springcourse.service.OpportunityService;
import com.education.uopp.springcourse.service.SkillService;
import com.education.uopp.springcourse.service.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class StudentProfileController {

    private StudentService studentService;
    private EditorService editorService;

    private SkillService skillService;

    private OpportunityService opportunityService;

    @ApiOperation(value = "Get student profile", notes = "Calling this endpoint you will get information about student.")
    @GetMapping("/profile/{id}")
    public ResponseEntity<Student> profile(
            @ApiParam(name = "id", example = "1", required = true, type = "Long", value = "Student id")
            @PathVariable Long id) {
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Unlike opportunity", notes = "Calling this endpoint you will set this opportunity for current user as unliked.")
    @PutMapping("/profile/{studentId}/unlike/{opportunityId}")
    public ResponseEntity<Student> unlikeOpportunity(
            @ApiParam(name = "sort", example = "3", required = true, type = "Long", value = "Student id")
            @PathVariable Long studentId,
            @ApiParam(name = "opportunityId", example = "4", required = true, type = "Long", value = "Opportunity id to be unliked")
            @PathVariable Long opportunityId) {
        Student student = studentService.findById(studentId);
        studentService.likeUnlikeOpportunity(
                student,
                opportunityService.findById(opportunityId)
        );
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @ApiOperation(value = "Update student", notes = "Calling this endpoint you will student information.")
    @PutMapping("/profile/{id}/save")
    public ResponseEntity<Student> saveProfile(
            @ApiParam(name = "studentDto", required = true, type = "StudentDto", value = "Student information")
            @RequestBody StudentDto studentDto,
            @ApiParam(name = "id", example = "4", required = true, type = "Long", value = "Student id to be updated")
            @PathVariable Long id) {
        if (!studentService.checkIfEmailAvailable(studentDto.getEmail())
                && !editorService.checkIfEmailAvailable(studentDto.getEmail())) {
            return new ResponseEntity<>(HttpStatus.IM_USED);
        }

        Set<Skill> skillSet = Arrays.stream(studentDto.getSkills())
                .map(skillService::findByType)
                .collect(Collectors.toSet());

        Student updated = studentService.update(studentDto.toStudent(skillSet), studentService.findById(id));
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
