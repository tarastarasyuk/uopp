package com.education.uopp.springcourse.service;

import com.education.uopp.springcourse.model.Editor;
import com.education.uopp.springcourse.repository.EditorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EditorService {

    private final EditorRepository editorRepository;

    public Editor create(Editor entity) {
        return editorRepository.create(entity);
    }

    public Editor findById(Long id) {
        return editorRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Editor with id %d not found".formatted(id));
        });
    }

    public List<Editor> findAll() {
        return editorRepository.findAll();
    }

    public Editor update(Editor source, Editor target) {
        return editorRepository.update(source, target);
    }

    public void delete(Long id) {
        editorRepository.delete(id);
    }

    public boolean checkIfEmailAvailable(String email) {
        return editorRepository.checkIfEmailAvailable(email);
    }

    public Editor findByEmail(String email) {
        return editorRepository.findByEmail(email).orElseThrow(() -> {
            throw new RuntimeException("Editor with email '%s' not found".formatted(email));
        });
    }
}
