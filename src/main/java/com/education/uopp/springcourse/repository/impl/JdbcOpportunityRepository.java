package com.education.uopp.springcourse.repository.impl;

import com.education.uopp.springcourse.model.Opportunity;
import com.education.uopp.springcourse.repository.OpportunityRepository;
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
public class JdbcOpportunityRepository implements OpportunityRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Opportunity create(Opportunity entity) {
        String createOpportunitySql = "INSERT INTO opportunity (name, deadline, asap, content) VALUES (?, ?, ?, ?)";
        try {
            jdbcTemplate.update(
                    createOpportunitySql,
                    entity.getName(), entity.getDeadline(), entity.getASAP(), entity.getContent()
            );
            Opportunity created = findLastOpportunity();
            log.info("New opportunity created: {}", created);
            return created;
        } catch (Exception ex) {
            log.error("Cannot create new opportunity {}: {}", entity, ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    private Opportunity findLastOpportunity() {
        String findLastOpportunitySql = "SELECT * FROM opportunity ORDER BY id DESC LIMIT 1";
        return jdbcTemplate.queryForObject(findLastOpportunitySql, new BeanPropertyRowMapper<>(Opportunity.class));
    }

    @Override
    public Optional<Opportunity> findById(Long id) {
        String findOpportunityById = "SELECT * FROM opportunity WHERE id = ?";
        try {
            Optional<Opportunity> opportunity = jdbcTemplate.queryForObject(
                    findOpportunityById,
                    (rs, rowNum) -> Optional.of(new Opportunity(
                            rs.getLong(1),
                            rs.getString(2),
                            rs.getDate(3),
                            rs.getBoolean(4),
                            rs.getString(5),
                            rs.getDate(6)
                    )),
                    id
            );
            log.info("Opportunity with id {} found: {}", id, opportunity.get());
            return opportunity;
        } catch (Exception ex) {
            log.error("Opportunity with id {} not found: {}", id, ex.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Opportunity> findAll() {
        String findAllOpportunitiesSql = "SELECT * FROM opportunity";
        List<Opportunity> allOpportunities = jdbcTemplate.query(
                findAllOpportunitiesSql,
                (rs, rowNum) -> new Opportunity(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getBoolean(4),
                        rs.getString(5),
                        rs.getDate(6)
                )
        );
        log.info("{} opportunities found: {}", allOpportunities.size(), allOpportunities);
        return allOpportunities;
    }

    @Override
    @Transactional
    public Opportunity update(Opportunity source, Opportunity target) {
        String updateOpportunitySql = "UPDATE opportunity SET name = ?, deadline = ?, asap = ?, content = ? WHERE id = ?";
        try {
            jdbcTemplate.update(
                    updateOpportunitySql,
                    source.getName(), source.getDeadline(), source.getASAP(), source.getContent(), target.getId()
            );
            Opportunity updated = findById(target.getId()).get();
            log.info("Opportunity {} updated: {}", target, updated);
            return updated;
        } catch (Exception ex) {
            log.error("Cannot update opportunity {} -> {}: {}", target, source, ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        deleteStudentLikedOpportunitiesByOpportunityId(id);
        String deleteOpportunitySql = "DELETE FROM opportunity WHERE id = ?";
        jdbcTemplate.update(deleteOpportunitySql, id);
        log.info("Opportunity with id {} deleted", id);
    }

    private void deleteStudentLikedOpportunitiesByOpportunityId(long opportunityId) {
        String deleteStudentLikedOpportunitiesByOpportunityIdSql = "DELETE FROM student_has_opportunity " +
                "WHERE opportunity_id = ?";

        jdbcTemplate.update(deleteStudentLikedOpportunitiesByOpportunityIdSql, opportunityId);
    }
}
