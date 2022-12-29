package com.education.uopp.springcourse.service;

import com.education.uopp.domain.entity.Student;
import com.education.uopp.springcourse.model.SCOpportunity;
import com.education.uopp.springcourse.repository.SCStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final SCStudentRepository studentRepository;

    public Student create(Student entity) {
        return studentRepository.save(entity);
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
        if (studentRepository.existsByEmail(source.getEmail()) && !source.getEmail().equals(target.getEmail())) {
            throw new RuntimeException("Student with email '%s' is already exists".formatted(target.getEmail()));
        }
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setAge(source.getAge());
        target.setPhone(source.getPhone());
        return studentRepository.save(target);
    }

    public void delete(Student entity) {
        studentRepository.delete(entity);
    }

    public void likeUnlikeOpportunity(Student student, SCOpportunity opportunity) {
        Set<SCOpportunity> studentLikedOpportunities = student.getLikedOpportunities();
        if (studentLikedOpportunities.contains(opportunity)) {
            studentLikedOpportunities.remove(opportunity);
        } else {
            studentLikedOpportunities.add(opportunity);
        }
        studentRepository.save(student);
    }

    public Student findByEmail(String email) {
        return studentRepository.findByEmail(email).orElseThrow(() -> {
            throw new RuntimeException("Student with email '%s' not found".formatted(email));
        });
    }

    public boolean existsByEmail(String email) {
        return studentRepository.existsByEmail(email);
    }
}
