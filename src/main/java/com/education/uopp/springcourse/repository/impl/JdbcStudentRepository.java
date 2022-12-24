package com.education.uopp.springcourse.repository.impl;

import com.education.uopp.springcourse.model.Opportunity;
import com.education.uopp.springcourse.model.Role;
import com.education.uopp.springcourse.model.Skill;
import com.education.uopp.springcourse.model.Student;
import com.education.uopp.springcourse.repository.OpportunityRepository;
import com.education.uopp.springcourse.repository.SkillRepository;
import com.education.uopp.springcourse.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JdbcStudentRepository implements StudentRepository {
    private final JdbcTemplate jdbcTemplate;

    private final SkillRepository skillRepository;
    private final OpportunityRepository opportunityRepository;

    @Override
    @Transactional
    public Student create(Student entity) {
        try {
            checkEmail(entity.getEmail());

            long[] ids = createStudentAndReturnUserAndStudentIds(entity);
            Set<Skill> skills = insertStudentSkills(ids[1], entity.getSkills());
            Student created = new Student(
                    ids[0], entity.getEmail(),
                    ids[1], entity.getFirstName(), entity.getLastName(), entity.getAge(), entity.getPhone(),
                    skills, new HashSet<>()
            );
            log.info("New student created: {}", created);
            return created;
        } catch (Exception ex) {
            log.error("Cannot create new student {}: {}", entity, ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    private long[] createStudentAndReturnUserAndStudentIds(Student entity) {
        String createStudentSql = "INSERT INTO student (firstname, lastname, age, phone, user_id) " +
                "VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        long userId = createUserAndReturnUserId(jdbcTemplate, entity.getEmail(), Role.ROLE_STUDENT);
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(createStudentSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setInt(3, entity.getAge());
            ps.setString(4, entity.getPhone());
            ps.setLong(5, userId);
            return ps;
        }, keyHolder);
        return new long[]{userId, keyHolder.getKey().longValue()};
    }

    private Set<Skill> insertStudentSkills(long studentId, Set<Skill> skills) {
        List<Long> skillsIds = skills.stream()
                .flatMap(skill -> skillRepository.findByType(skill.getType()).stream().map(Skill::getId))
                .toList();

        String insertStudentSkillsSql = "INSERT INTO student_has_skill (student_id, skill_id) VALUES (?, ?)";
        jdbcTemplate.batchUpdate(
                insertStudentSkillsSql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, studentId);
                        ps.setLong(2, skillsIds.get(i));
                    }

                    @Override
                    public int getBatchSize() {
                        return skillsIds.size();
                    }
                }
        );

        return skillsIds.stream()
                .flatMap(skillId -> skillRepository.findById(skillId).stream())
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Student> findById(Long id) {
        String findStudentByIdSql = "SELECT student.id, student.firstname, student.lastname, student.age, " +
                "student.phone, student.user_id, user.id, user.email " +
                "FROM student " +
                "INNER JOIN user ON user.id = student.user_id " +
                "WHERE student.id = ?";

        try {
            Optional<Student> student = jdbcTemplate.queryForObject(
                    findStudentByIdSql,
                    (rs, rowNum) -> Optional.of(new Student(
                            rs.getLong("user.id"),
                            rs.getString("user.email"),
                            rs.getLong("student.id"),
                            rs.getString("student.firstname"),
                            rs.getString("student.lastname"),
                            rs.getInt("student.age"),
                            rs.getString("student.phone"),
                            getStudentSkillsByStudentId(id),
                            getStudentLikedOpportunitiesByStudentId(id)
                    )),
                    id
            );
            log.info("Student with id {} found: {}", id, student.get());
            return student;
        } catch (Exception ex) {
            log.error("Student with id {} not found: {}", id, ex.getMessage());
            return Optional.empty();
        }
    }

    private Set<Skill> getStudentSkillsByStudentId(long studentId) {
        String getStudentSkillsByStudentIdSql = "SELECT skill_id FROM student_has_skill WHERE student_id = ?";
        Set<Skill> studentSkills = new HashSet<>();
        jdbcTemplate.query(
                getStudentSkillsByStudentIdSql,
                rs -> {
                    studentSkills.add(skillRepository.findById(rs.getLong(1)).get());
                },
                studentId
        );
        return studentSkills;
    }

    private Set<Opportunity> getStudentLikedOpportunitiesByStudentId(long studentId) {
        String getStudentLikedOpportunitiesByStudentId = "SELECT opportunity_id FROM student_has_opportunity " +
                "WHERE student_id = ?";

        Set<Opportunity> studentLikedOpportunities = new HashSet<>();
        jdbcTemplate.query(
                getStudentLikedOpportunitiesByStudentId,
                rs -> {
                    studentLikedOpportunities.add(opportunityRepository.findById(rs.getLong(1)).get());
                },
                studentId
        );
        return studentLikedOpportunities;
    }

    @Override
    public List<Student> findAll() {
        String findAllStudentsSql = "SELECT student.id, student.firstname, student.lastname, student.age, " +
                "student.phone, student.user_id, user.id, user.email " +
                "FROM student " +
                "INNER JOIN user ON user.id = student.user_id";

        List<Student> allStudents = jdbcTemplate.query(
                findAllStudentsSql,
                (rs, rowNum) -> {
                    long studentId;
                    return new Student(
                            rs.getLong("user.id"),
                            rs.getString("user.email"),
                            studentId = rs.getLong("student.id"),
                            rs.getString("student.firstname"),
                            rs.getString("student.lastname"),
                            rs.getInt("student.age"),
                            rs.getString("student.phone"),
                            getStudentSkillsByStudentId(studentId),
                            getStudentLikedOpportunitiesByStudentId(studentId)
                    );
                }
        );
        log.info("{} students found: {}", allStudents.size(), allStudents);
        return allStudents;
    }

    @Override
    public Student update(Student source, Student target) {
        try {
            if (!source.getEmail().equals(target.getEmail())) {
                checkEmail(source.getEmail());

                String updateStudentEmailSql = "UPDATE user SET user.email = ? WHERE user.id = ?";
                jdbcTemplate.update(updateStudentEmailSql, source.getEmail(), getUserIdByStudentId(target.getId()));
            }

            String updateStudentSql = "UPDATE student " +
                    "SET student.firstname = ?, student.lastname = ?, student.age = ?, student.phone = ? " +
                    "WHERE student.id = ?";

            jdbcTemplate.update(
                    updateStudentSql,
                    source.getFirstName(), source.getLastName(), source.getAge(), source.getPhone(), target.getId()
            );
            Student updated = findById(target.getId()).get();
            log.info("Student {} updated: {}", target, updated);
            return updated;
        } catch (Exception ex) {
            log.error("Cannot update student {} -> {}: {}", target, source, ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    private long getUserIdByStudentId(long studentId) {
        String getUserIdByStudentIdSql = "SELECT user_id from student WHERE id = ?";
        Long userId = jdbcTemplate.queryForObject(getUserIdByStudentIdSql, Long.class, studentId);
        if (userId != null) return userId;
        throw new RuntimeException("Student with id %d does not exist".formatted(studentId));
    }

    @Override
    public void delete(Long id) {
        deleteStudentSkillsByStudentId(id);
        deleteStudentLikedOpportunitiesByStudentId(id);

        String deleteStudentAndUserSql = "DELETE student, user " +
                "FROM student " +
                "JOIN user ON user.id = student.user_id " +
                "WHERE student.id = ?";

        jdbcTemplate.update(deleteStudentAndUserSql, id);
        log.info("Student with id {} deleted", id);
    }

    private void deleteStudentSkillsByStudentId(long studentId) {
        String deleteStudentSkillsByStudentIdSql = "DELETE FROM student_has_skill WHERE student_id = ?";
        jdbcTemplate.update(deleteStudentSkillsByStudentIdSql, studentId);
    }

    private void deleteStudentLikedOpportunitiesByStudentId(long studentId) {
        String deleteStudentLikedOpportunitiesByStudentIdSql = "DELETE FROM student_has_opportunity " +
                "WHERE student_id = ?";

        jdbcTemplate.update(deleteStudentLikedOpportunitiesByStudentIdSql, studentId);
    }

    @Override
    public void likeUnlikeOpportunity(Student student, Opportunity opportunity) {
        if (student.getLikedOpportunities().contains(opportunity)) {
            student.getLikedOpportunities().remove(opportunity);
            unlikeOpportunity(student.getId(), opportunity.getId());
        } else {
            student.getLikedOpportunities().add(opportunity);
            likeOpportunity(student.getId(), opportunity.getId());
        }
    }

    private void likeOpportunity(long studentId, long opportunityId) {
        String likeOpportunitySql = "INSERT INTO student_has_opportunity (student_id, opportunity_id) VALUES (?, ?)";
        jdbcTemplate.update(likeOpportunitySql, studentId, opportunityId);
    }

    private void unlikeOpportunity(long studentId, long opportunityId) {
        String unlikeOpportunity = "DELETE FROM student_has_opportunity WHERE student_id = ? AND opportunity_id = ?";
        jdbcTemplate.update(unlikeOpportunity, studentId, opportunityId);
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        String findStudentByIdSql = "SELECT  user.id, user.email, " +
                "student.id, student.firstname, student.lastname, student.age, student.phone, student.user_id " +
                "FROM user " +
                "INNER JOIN student ON student.user_id = user.id " +
                "WHERE user.email = ?";

        try {
            Optional<Student> student = jdbcTemplate.queryForObject(
                    findStudentByIdSql,
                    (rs, rowNum) -> {
                        long studentId;
                        return Optional.of(new Student(
                                rs.getLong("user.id"),
                                rs.getString("user.email"),
                                studentId = rs.getLong("student.id"),
                                rs.getString("student.firstname"),
                                rs.getString("student.lastname"),
                                rs.getInt("student.age"),
                                rs.getString("student.phone"),
                                getStudentSkillsByStudentId(studentId),
                                getStudentLikedOpportunitiesByStudentId(studentId)
                        ));
                    },
                    email
            );
            log.info("Student with email '{}' found: {}", email, student.get());
            return student;
        } catch (Exception ex) {
            log.error("Student with email '{}' not found: {}", email, ex.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public boolean checkIfEmailAvailable(String email) {
        return checkIfEmailAvailable(jdbcTemplate, email);
    }
}
