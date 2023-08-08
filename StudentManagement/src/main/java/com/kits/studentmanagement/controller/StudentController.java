package com.kits.studentmanagement.controller;

import com.kits.studentmanagement.model.Student;
import com.kits.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = studentService.findAllStudents();
        return ResponseEntity.ok(students);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        Optional<Student> student = studentService.findByStudentId(id);
        if (student.isPresent()){
            return ResponseEntity.ok(student.get());
        } else
            return ResponseEntity.notFound().build();
    }
    @PostMapping("")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        Student addStudent = studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(addStudent);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student){
        Optional<Student> existingStudent = studentService.findByStudentId(id);
        if (existingStudent != null) {
            student.setId(id);
            Student updatedStudent = studentService.updateStudent(id,student);
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        Optional<Student> existingStudent = studentService.findByStudentId(id);
        if (existingStudent != null) {
            studentService.deleteStudent(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
