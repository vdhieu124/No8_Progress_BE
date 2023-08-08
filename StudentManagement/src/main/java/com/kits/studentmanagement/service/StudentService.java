package com.kits.studentmanagement.service;

import com.kits.studentmanagement.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> findAllStudents();
    Optional<Student> findByStudentId(Long id);
    Student findByName(String name);
    Student addStudent(Student student);
    Student updateStudent(Long id, Student student);
    boolean deleteStudent(Long id);
}
