package com.education.uopp.springcourse.controller;

import com.education.uopp.springcourse.model.SCOpportunity;
import com.education.uopp.springcourse.model.SCStudent;
import com.education.uopp.springcourse.service.OpportunityService;
import com.education.uopp.springcourse.service.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/opportunities")
@CrossOrigin
public class OpportunityController {

    private OpportunityService opportunitiesService;
    private StudentService studentService;

    @ApiOperation(value = "Get all opportunities", notes = "Calling this endpoint you will get all existing opportunities. Also you can sort them.")
    @GetMapping("")
    public ResponseEntity<List<SCOpportunity>> getAll(
            @ApiParam(name = "sort", example = "asap", type = "String", value = "The sorting option")
            @RequestParam(value = "sort", required = false) String sort) {
        return new ResponseEntity<>(opportunitiesService.findAll(sort), HttpStatus.OK);
    }

    @ApiOperation(value = "Like opportunity", notes = "Calling this endpoint you will set this opportunity for current user as liked.")
    @PutMapping("/profile/{studentId}/like/{opportunityId}")
    public ResponseEntity<SCStudent> likeOpportunity(
            @ApiParam(name = "studentId", example = "3", required = true, type = "String", value = "Student id")
            @PathVariable Long studentId,
            @ApiParam(name = "opportunityId", example = "4", required = true, type = "String", value = "Opportunity id to be liked")
            @PathVariable Long opportunityId) {
        SCStudent student = studentService.findById(studentId);
        toggleLike(opportunityId, student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @ApiOperation(value = "Unlike opportunity", notes = "Calling this endpoint you will set this opportunity for current user as unliked.")
    @PutMapping("/profile/{studentId}/unlike/{opportunityId}")
    public ResponseEntity<SCStudent> unlikeOpportunity(
            @ApiParam(name = "studentId", example = "3", required = true, type = "String", value = "Student id")
            @PathVariable Long studentId,
            @ApiParam(name = "opportunityId", example = "4", required = true, type = "String", value = "Opportunity id to be unliked")
            @PathVariable Long opportunityId) {
        SCStudent student = studentService.findById(studentId);
        toggleLike(opportunityId, student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    private void toggleLike(Long opportunityId, SCStudent student) {
        studentService.likeUnlikeOpportunity(student, opportunitiesService.findById(opportunityId));
    }
}
