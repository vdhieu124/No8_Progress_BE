package com.kits.studentmanagement.service;

import com.kits.studentmanagement.model.Student;
import com.kits.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findByStudentId(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student findByName(String name) {
        return studentRepository.findByName(name);
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long id, Student newStudent) {
        Optional<Student> student = studentRepository.findById(id);
        student.ifPresent(s -> {
            s.setName(newStudent.getName());
            s.setGender(newStudent.getGender());
            s.setDob(newStudent.getDob());
            s.setClassA(newStudent.getClassA());
            studentRepository.save(s);
        });
        return newStudent;
    }

    @Override
    public boolean deleteStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
