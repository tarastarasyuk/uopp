package com.education.uopp.springcourse.repository.impl;

import com.education.uopp.springcourse.model.Editor;
import com.education.uopp.springcourse.model.Role;
import com.education.uopp.springcourse.repository.EditorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JdbcEditorRepository implements EditorRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Editor create(Editor entity) {
        try {
            checkEmail(entity.getEmail());

            long[] ids = createEditorAndReturnUserAndEditorIds(entity);
            Editor created = new Editor(ids[0], entity.getEmail(), ids[1]);
            log.info("New editor created: {}", created);
            return created;
        } catch (Exception ex) {
            log.error("Cannot create new editor {}: {}", entity, ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    private long[] createEditorAndReturnUserAndEditorIds(Editor entity) {
        String createEditorSql = "INSERT INTO editor (user_id) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        long userId = createUserAndReturnUserId(jdbcTemplate, entity.getEmail(), Role.ROLE_EDITOR);
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(createEditorSql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, userId);
            return ps;
        }, keyHolder);
        return new long[]{userId, keyHolder.getKey().longValue()};
    }

    @Override
    public Optional<Editor> findById(Long id) {
        String findEditorByIdSql = "SELECT editor.id, editor.user_id, user.id, user.email " +
                "FROM editor " +
                "INNER JOIN user ON user.id = editor.user_id " +
                "WHERE editor.id = ?";

        try {
            Optional<Editor> editor = jdbcTemplate.queryForObject(
                    findEditorByIdSql,
                    (rs, rowNum) -> Optional.of(new Editor(
                            rs.getLong("user.id"),
                            rs.getString("user.email"),
                            rs.getLong("editor.id")
                    )),
                    id
            );
            log.info("Editor with id {} found: {}", id, editor.get());
            return editor;
        } catch (Exception ex) {
            log.error("Editor with id {} not found: {}", id, ex.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Editor> findAll() {
        String findAllEditorsSql = "SELECT editor.id, editor.user_id, user.id, user.email " +
                "FROM editor " +
                "INNER JOIN user ON user.id = editor.user_id";

        List<Editor> allEditors = jdbcTemplate.query(
                findAllEditorsSql,
                (rs, rowNum) -> new Editor(
                        rs.getLong("user.id"),
                        rs.getString("user.email"),
                        rs.getLong("editor.id")
                )
        );
        log.info("{} editors found: {}", allEditors.size(), allEditors);
        return allEditors;
    }

    @Override
    @Transactional
    public Editor update(Editor source, Editor target) {
        try {
            if (!source.getEmail().equals(target.getEmail())) {
                checkEmail(source.getEmail());
            }

            String updateUserSql = "UPDATE user SET user.email = ? WHERE user.id = ?";
            jdbcTemplate.update(updateUserSql, source.getEmail(), getUserIdByEditorId(target.getId()));
            Editor updated = findById(target.getId()).get();
            log.info("Editor {} updated: {}", target, updated);
            return updated;
        } catch (Exception ex) {
            log.error("Cannot update editor {} -> {}: {}", target, source, ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    private long getUserIdByEditorId(long editorId) {
        String getUserIdSql = "SELECT user_id from editor WHERE id = ?";
        Long userId = jdbcTemplate.queryForObject(getUserIdSql, Long.class, editorId);
        if (userId != null) return userId;
        throw new RuntimeException("Editor with id %d does not exist".formatted(editorId));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        String deleteEditorAndUserSql = "DELETE editor, user " +
                "FROM editor " +
                "JOIN user ON user.id = editor.user_id " +
                "WHERE editor.id = ?";

        jdbcTemplate.update(deleteEditorAndUserSql, id);
        log.info("Editor with id {} deleted", id);
    }

    @Override
    public Optional<Editor> findByEmail(String email) {
        String findEditorByIdSql = "SELECT user.id, user.email, editor.id, editor.user_id " +
                "FROM user " +
                "INNER JOIN editor ON editor.user_id = user.id " +
                "WHERE user.email = ?";

        try {
            Optional<Editor> editor = jdbcTemplate.queryForObject(
                    findEditorByIdSql,
                    (rs, rowNum) -> Optional.of(new Editor(
                            rs.getLong("user.id"),
                            rs.getString("user.email"),
                            rs.getLong("editor.id")
                    )),
                    email
            );
            log.info("Editor with email '{}' found: {}", email, editor.get());
            return editor;
        } catch (Exception ex) {
            log.error("Editor with email '{}' not found: {}", email, ex.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public boolean checkIfEmailAvailable(String email) {
        return checkIfEmailAvailable(jdbcTemplate, email);
    }
}
