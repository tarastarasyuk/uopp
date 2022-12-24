package com.education.uopp.springcourse.repository;

import com.education.uopp.springcourse.model.Role;
import com.education.uopp.springcourse.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

public interface UserRepository<T extends User, ID> extends CrudRepository<T, ID> {
    Optional<T> findByEmail(String email);

    boolean checkIfEmailAvailable(String email);

    default long createUserAndReturnUserId(JdbcTemplate jdbcTemplate, String email, Role role) {
        String createUserSql = "INSERT INTO user (email, role) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(createUserSql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, email);
                ps.setString(2, role.name());
                return ps;
            }, keyHolder);
            return keyHolder.getKey().longValue();
        } catch (Exception ex) {
            throw new RuntimeException("Cannot create new user with email '%s': %s".formatted(email, ex.getMessage()));
        }
    }

    default boolean checkIfEmailAvailable(JdbcTemplate jdbcTemplate, String email) {
        String checkIfEmailAvailableSql = "SELECT COUNT(email) FROM user WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(checkIfEmailAvailableSql, Integer.class, email);
        return count == null || count == 0;
    }

    default void checkEmail(String email) {
        if (!checkIfEmailAvailable(email)) {
            throw new RuntimeException("Email '%s' is already taken".formatted(email));
        }
    }
}
