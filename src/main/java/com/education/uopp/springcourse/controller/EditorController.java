package com.education.uopp.springcourse.controller;

import com.education.uopp.springcourse.dto.OpportunityDto;
import com.education.uopp.springcourse.model.SCOpportunity;
import com.education.uopp.springcourse.service.OpportunityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editor/opportunities")
@CrossOrigin
public class EditorController {

    @Autowired
    private OpportunityService opportunitiesService;

    @ApiOperation(value = "Get all opportunities", notes = "Calling this endpoint you will get all existing opportunities. Also you can sort them.")
    @GetMapping("")
    public ResponseEntity<List<SCOpportunity>> getAll(
            @ApiParam(name = "sort", example = "asap", required = false, type = "String", value = "The sorting option")
            @RequestParam(value = "sort", required = false) String sort) {
        return new ResponseEntity<>(opportunitiesService.findAll(sort), HttpStatus.OK);
    }

    @ApiOperation(value = "Create an opportunity", notes = "Calling this endpoint you will create a new opportunity")
    @PostMapping("/create")
    public ResponseEntity<SCOpportunity> createOpportunity(
            @ApiParam(name = "opportunityDto", required = true, type = "OpportunityDto", value = "Opportunity information")
            @RequestBody OpportunityDto opportunityDto) {
        return new ResponseEntity<>(opportunitiesService.create(opportunityDto.toOpportunity()), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Edit an opportunity", notes = "Calling this endpoint you will edit an existing opportunity")
    @PutMapping("/edit/{id}")
    public ResponseEntity<SCOpportunity> editOpportunity(
            @ApiParam(name = "opportunityDto", required = true, type = "OpportunityDto", value = "New opportunity information")
            @RequestBody OpportunityDto opportunityDto,
            @ApiParam(name = "id", required = true, type = "Long", value = "Path variable that defines which opportunity should be edited")
            @PathVariable Long id) {
        SCOpportunity editedOpportunity = opportunitiesService.update(opportunityDto.toOpportunity(),
                opportunitiesService.findById(id));
        return new ResponseEntity<>(editedOpportunity, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete an opportunity", notes = "Calling this endpoint you will delete an existing opportunity")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteOpportunity(
            @ApiParam(name = "id", required = true, type = "Long", value = "Path variable that defines which opportunity should be deleted")
            @PathVariable Long id) {
        opportunitiesService.delete(opportunitiesService.findById(id));
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
