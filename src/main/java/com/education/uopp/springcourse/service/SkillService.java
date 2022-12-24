package com.education.uopp.springcourse.service;

import com.education.uopp.springcourse.model.Skill;
import com.education.uopp.springcourse.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    public Skill create(Skill entity) {
        return skillRepository.create(entity);
    }

    public Skill findById(Long id) {
        return skillRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Skill with id %d not found".formatted(id));
        });
    }

    public Skill findByType(String type) {
        return skillRepository.findByType(type).orElseThrow(() -> {
            throw new RuntimeException("Skill with type '%s' not found".formatted(type));
        });
    }

    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    public Skill update(Skill source, Skill target) {
        return skillRepository.update(source, target);
    }

    public void delete(Long id) {
        skillRepository.delete(id);
    }
}
