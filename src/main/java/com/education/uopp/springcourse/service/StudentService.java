package com.education.uopp.springcourse.service;

import com.education.uopp.springcourse.model.Opportunity;
import com.education.uopp.springcourse.model.Student;
import com.education.uopp.springcourse.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student create(Student entity) {
        return studentRepository.create(entity);
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Student with id %s not found".formatted(id));
        });
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student update(Student source, Student target) {
        return studentRepository.update(source, target);
    }

    public void delete(Long id) {
        studentRepository.delete(id);
    }

    public Student likeUnlikeOpportunity(Student student, Opportunity opportunity) {
        studentRepository.likeUnlikeOpportunity(student, opportunity);
        return findById(student.getId());
    }

    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email).orElseThrow(() -> {
            throw new RuntimeException("Student with email '%s' not found".formatted(email));
        });
    }

    public boolean isLiked(Student student, Opportunity opportunity) {
        return student.getLikedOpportunities().contains(opportunity);
    }

    public boolean isLiked(String userEmail, Opportunity opportunity) {
        return findByEmail(userEmail).getLikedOpportunities().contains(opportunity);
    }

    public boolean checkIfEmailAvailable(String email) {
        return studentRepository.checkIfEmailAvailable(email);
    }
}
