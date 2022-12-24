package com.education.uopp.springcourse.repository.impl;

import com.education.uopp.springcourse.model.Skill;
import com.education.uopp.springcourse.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JdbcSkillRepository implements SkillRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Skill create(Skill entity) {
        try {
            checkType(entity.getType());

            String createSkillSql = "INSERT INTO skill (type) VALUES (?)";
            jdbcTemplate.update(createSkillSql, entity.getType());
            Skill created = findLastSkill();
            log.info("New skill created: {}", created);
            return created;
        } catch (Exception ex) {
            log.error("Cannot create new skill {}: {}", entity, ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    private void checkType(String type) {
        String checkIfTypeAvailable = "SELECT COUNT(type) FROM skill WHERE type = ?";
        Integer count = jdbcTemplate.queryForObject(checkIfTypeAvailable, Integer.class, type);
        if (count != null && count != 0) {
            throw new RuntimeException(
                    "Type '%s' is already taken".formatted(type)
            );
        }
    }

    private Skill findLastSkill() {
        String findLastSkillSql = "SELECT * FROM skill ORDER BY id DESC LIMIT 1";
        return jdbcTemplate.queryForObject(findLastSkillSql, new BeanPropertyRowMapper<>(Skill.class));
    }

    @Override
    public Optional<Skill> findById(Long id) {
        String findSkillByIdSql = "SELECT * FROM skill WHERE id = ?";
        try {
            Optional<Skill> skill = jdbcTemplate.queryForObject(
                    findSkillByIdSql,
                    (rs, rowNum) -> Optional.of(new Skill(
                            rs.getLong(1),
                            rs.getString(2)
                    )),
                    id
            );
            log.info("Skill with id {} found: {}", id, skill.get());
            return skill;
        } catch (Exception ex) {
            log.error("Skill with id {} not found: {}", id, ex.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Skill> findAll() {
        String findAllSkillsSql = "SELECT * FROM skill";
        List<Skill> allSkills = jdbcTemplate.query(
                findAllSkillsSql,
                (rs, rowNum) -> new Skill(
                        rs.getLong(1),
                        rs.getString(2)
                )
        );
        log.info("{} skills found: {}", allSkills.size(), allSkills);
        return allSkills;
    }

    @Override
    public Skill update(Skill source, Skill target) {
        try {
            if (!source.getType().equals(target.getType())) {
                checkType(source.getType());
            }

            String updateSkillSql = "UPDATE skill SET type = ? WHERE id = ?";

            jdbcTemplate.update(updateSkillSql, source.getType(), target.getId());
            Skill updated = findById(target.getId()).get();
            log.info("Skill {} updated: {}", target, updated);
            return updated;
        } catch (Exception ex) {
            log.error("Cannot update skill {} -> {}: {}", target, source, ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Long id) {
        deleteStudentSkillsBySkillId(id);
        String deleteSkillSql = "DELETE FROM skill WHERE id = ?";
        jdbcTemplate.update(deleteSkillSql, id);
        log.info("Skill with id {} deleted", id);
    }

    private void deleteStudentSkillsBySkillId(long skillId) {
        String deleteStudentSkillsBySkillIdSql = "DELETE FROM student_has_skill WHERE skill_id = ?";
        jdbcTemplate.update(deleteStudentSkillsBySkillIdSql, skillId);
    }

    @Override
    public Optional<Skill> findByType(String type) {
        String findSkillByTypeSql = "SELECT * FROM skill WHERE type = ?";
        try {
            Optional<Skill> skill = jdbcTemplate.queryForObject(
                    findSkillByTypeSql,
                    (rs, rowNum) -> Optional.of(new Skill(
                            rs.getLong(1),
                            rs.getString(2)
                    )),
                    type
            );
            log.info("Skill with type '{}' found: {}", type, skill.get());
            return skill;
        } catch (Exception ex) {
            log.error("Skill with type '{}' not found: {}", type, ex.getMessage());
            return Optional.empty();
        }
    }
}
