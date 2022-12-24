package com.education.uopp.springcourse.repository;

import com.education.uopp.springcourse.model.Skill;

import java.util.Optional;

public interface SkillRepository extends CrudRepository<Skill, Long> {
    Optional<Skill> findByType(String type);
}
