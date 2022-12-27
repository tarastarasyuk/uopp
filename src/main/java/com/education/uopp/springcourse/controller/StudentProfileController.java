package com.education.uopp.springcourse.controller;

import com.education.uopp.springcourse.dto.StudentDto;
import com.education.uopp.springcourse.model.SCOpportunity;
import com.education.uopp.springcourse.model.SCSkill;
import com.education.uopp.springcourse.model.SCStudent;
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
    public ResponseEntity<SCStudent> profile(
            @ApiParam(name = "id", example = "1", required = true, type = "Long", value = "Student id")
            @PathVariable Long id) {
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Unlike opportunity", notes = "Calling this endpoint you will set this opportunity for current user as unliked.")
    @PutMapping("/profile/{studentId}/unlike/{opportunityId}")
    public ResponseEntity<SCStudent> unlikeOpportunity(
            @ApiParam(name = "sort", example = "3", required = true, type = "Long", value = "Student id")
            @PathVariable Long studentId,
            @ApiParam(name = "opportunityId", example = "4", required = true, type = "Long", value = "Opportunity id to be unliked")
            @PathVariable Long opportunityId) {
        SCStudent student = studentService.findById(studentId);
        SCOpportunity opportunity = opportunityService.findById(opportunityId);
        studentService.likeUnlikeOpportunity(student, opportunity);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @ApiOperation(value = "Update student", notes = "Calling this endpoint you will student information.")
    @PutMapping("/profile/{id}/save")
    public ResponseEntity<SCStudent> saveProfile(
            @ApiParam(name = "studentDto", required = true, type = "StudentDto", value = "Student information")
            @RequestBody StudentDto studentDto,
            @ApiParam(name = "id", example = "4", required = true, type = "Long", value = "Student id to be updated")
            @PathVariable Long id) {
        Set<SCSkill> skillSet = Arrays.stream(studentDto.getSkills())
                .map(skillService::findByType)
                .collect(Collectors.toSet());

        SCStudent target = studentService.findById(id);
        SCStudent source = studentDto.toStudent(skillSet);

        if (!target.getEmail().equals(source.getEmail())
                && (studentService.existsByEmail(studentDto.getEmail()) || editorService.existsByEmail(studentDto.getEmail()))) {
            return new ResponseEntity<>(HttpStatus.IM_USED);
        }

        SCStudent updated = studentService.update(source, target);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
