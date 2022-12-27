package com.education.uopp.springcourse.controller;

import com.education.uopp.springcourse.dto.SkillDto;
import com.education.uopp.springcourse.model.SCSkill;
import com.education.uopp.springcourse.service.SkillService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
@RequiredArgsConstructor
@CrossOrigin
public class SkillController {
    private final SkillService skillService;

    @ApiOperation(value = "Create a skill", notes = "Calling this endpoint you will create a new skill")
    @PostMapping
    public ResponseEntity<SCSkill> create(
            @ApiParam(name = "skillDto", readOnly = true, type = "SkillDto", value = "Skill information")
            @RequestBody SkillDto skillDto) {
        return new ResponseEntity<>(skillService.create(skillDto.toSkill()), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get all skills", notes = "Calling this endpoint you will get all existing skills.")
    @GetMapping
    public ResponseEntity<List<SCSkill>> getAll() {
        return new ResponseEntity<>(skillService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get skill by id", notes = "Calling this endpoint you will get a skill by its id.")
    @GetMapping("/id/{skillId}")
    public ResponseEntity<SCSkill> getById(
            @ApiParam(name = "skillId", example = "1", required = true, type = "String", value = "Skill id")
            @PathVariable Long skillId) {
        return new ResponseEntity<>(skillService.findById(skillId), HttpStatus.OK);
    }

    @ApiOperation(value = "Get skill by type", notes = "Calling this endpoint you will get a skill by its type.")
    @GetMapping("/type/{skillType}")
    public ResponseEntity<SCSkill> getByType(
            @ApiParam(name = "skillType", example = "Java", required = true, type = "String", value = "Skill type")
            @PathVariable String skillType) {
        return new ResponseEntity<>(skillService.findByType(skillType), HttpStatus.OK);
    }
}
