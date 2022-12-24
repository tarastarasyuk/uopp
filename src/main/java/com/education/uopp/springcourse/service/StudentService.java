package com.education.uopp.springcourse.service;

import com.education.uopp.springcourse.model.SCOpportunity;
import com.education.uopp.springcourse.model.SCStudent;
import com.education.uopp.springcourse.repository.SCStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final SCStudentRepository studentRepository;

    public SCStudent create(SCStudent entity) {
        return studentRepository.save(entity);
    }

    public SCStudent findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Student with id %s not found".formatted(id));
        });
    }

    public List<SCStudent> findAll() {
        return studentRepository.findAll();
    }

    public SCStudent update(SCStudent source, SCStudent target) {
        if (studentRepository.existsByEmail(source.getEmail()) && !source.getEmail().equals(target.getEmail())) {
            throw new RuntimeException("Student with email '%s' is already exists".formatted(target.getEmail()));
        }
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setAge(source.getAge());
        target.setPhone(source.getPhone());
        return studentRepository.save(target);
    }

    public void delete(SCStudent entity) {
        studentRepository.delete(entity);
    }

    public void likeUnlikeOpportunity(SCStudent student, SCOpportunity opportunity) {
        Set<SCOpportunity> studentLikedOpportunities = student.getLikedOpportunities();
        if (studentLikedOpportunities.contains(opportunity)) {
            studentLikedOpportunities.remove(opportunity);
        } else {
            studentLikedOpportunities.add(opportunity);
        }
        studentRepository.save(student);
    }

    public SCStudent findByEmail(String email) {
        return studentRepository.findByEmail(email).orElseThrow(() -> {
            throw new RuntimeException("Student with email '%s' not found".formatted(email));
        });
    }

    public boolean existsByEmail(String email) {
        return studentRepository.existsByEmail(email);
    }
}
