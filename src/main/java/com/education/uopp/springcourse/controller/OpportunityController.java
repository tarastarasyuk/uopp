package com.education.uopp.springcourse.controller;

import com.education.uopp.springcourse.model.Opportunity;
import com.education.uopp.springcourse.model.Student;
import com.education.uopp.springcourse.service.OpportunityService;
import com.education.uopp.springcourse.service.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Setter
@RestController
@AllArgsConstructor
@RequestMapping("/opportunities")
public class OpportunityController {

    private OpportunityService opportunitiesService;
    private StudentService studentService;

    @ApiOperation(value = "Get all opportunities", notes = "Calling this endpoint you will get all existing opportunities. Also you can sort them.")
    @GetMapping("")
    public ResponseEntity<List<Opportunity>> getAll(
            @ApiParam(name = "sort", example = "asap", type = "String", value = "The sorting option")
            @RequestParam(value = "sort", required = false) String sort) {
        return new ResponseEntity<>(opportunitiesService.findAll(sort), HttpStatus.OK);
    }

    @ApiOperation(value = "Like opportunity", notes = "Calling this endpoint you will set this opportunity for current user as liked.")
    @PutMapping("/profile/{studentId}/like/{opportunityId}")
    public ResponseEntity<Student> likeOpportunity(
            @ApiParam(name = "studentId", example = "3", required = true, type = "String", value = "Student id")
            @PathVariable Long studentId,
            @ApiParam(name = "opportunityId", example = "4", required = true, type = "String", value = "Opportunity id to be liked")
            @PathVariable Long opportunityId) {
        Student student = toggleLike(opportunityId, studentService.findById(studentId));
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @ApiOperation(value = "Unlike opportunity", notes = "Calling this endpoint you will set this opportunity for current user as unliked.")
    @PutMapping("/profile/{studentId}/unlike/{opportunityId}")
    public ResponseEntity<Student> unlikeOpportunity(
            @ApiParam(name = "studentId", example = "3", required = true, type = "String", value = "Student id")
            @PathVariable Long studentId,
            @ApiParam(name = "opportunityId", example = "4", required = true, type = "String", value = "Opportunity id to be unliked")
            @PathVariable Long opportunityId) {
    Student student = toggleLike(opportunityId, studentService.findById(studentId));
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    private Student toggleLike(Long opportunityId, Student student) {
        return studentService.likeUnlikeOpportunity(
                student,
                opportunitiesService.findById(opportunityId)
        );
    }

}
