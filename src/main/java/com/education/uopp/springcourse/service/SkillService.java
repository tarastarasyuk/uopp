package com.education.uopp.springcourse.service;

import com.education.uopp.springcourse.model.SCSkill;
import com.education.uopp.springcourse.repository.SCSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SCSkillRepository skillRepository;

    public SCSkill create(SCSkill entity) {
        return skillRepository.save(entity);
    }

    public SCSkill findById(Long id) {
        return skillRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Skill with id %d not found".formatted(id));
        });
    }

    public SCSkill findByType(String type) {
        return skillRepository.findByType(type).orElseThrow(() -> {
            throw new RuntimeException("Skill with type '%s' not found".formatted(type));
        });
    }

    public List<SCSkill> findAll() {
        return skillRepository.findAll();
    }

    public SCSkill update(SCSkill source, SCSkill target) {
        if (source.getType().equals(target.getType())) {
            throw new RuntimeException("Skill with type '%s' is already exists".formatted(source.getType()));
        }
        target.setType(source.getType());
        return skillRepository.save(target);
    }

    public void delete(SCSkill entity) {
        skillRepository.delete(entity);
    }
}
