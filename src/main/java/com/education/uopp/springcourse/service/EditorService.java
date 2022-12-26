package com.education.uopp.springcourse.service;

import com.education.uopp.springcourse.model.SCEditor;
import com.education.uopp.springcourse.repository.SCEditorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EditorService {

    private final SCEditorRepository editorRepository;

    public SCEditor create(SCEditor entity) {
        return editorRepository.save(entity);
    }

    public SCEditor findById(Long id) {
        return editorRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Editor with id %d not found".formatted(id));
        });
    }

    public List<SCEditor> findAll() {
        return editorRepository.findAll();
    }

    public SCEditor update(SCEditor source, SCEditor target) {
        if (editorRepository.existsByEmail(source.getEmail()) && !source.getEmail().equals(target.getEmail())) {
            throw new RuntimeException("Editor with email '%s' is already exists".formatted(target.getEmail()));
        }
        target.setEmail(source.getEmail());
        return editorRepository.save(target);
    }

    public void delete(SCEditor entity) {
        editorRepository.delete(entity);
    }

    public SCEditor findByEmail(String email) {
        return editorRepository.findByEmail(email).orElseThrow(() -> {
            throw new RuntimeException("Editor with email '%s' not found".formatted(email));
        });
    }

    public boolean existsByEmail(String email) {
        return editorRepository.existsByEmail(email);
    }
}
