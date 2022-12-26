package com.education.uopp.springcourse.repository;

import com.education.uopp.springcourse.model.SCSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SCSkillRepository extends JpaRepository<SCSkill, Long> {
    Optional<SCSkill> findByType(String type);
}
