package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.model.CourseModel;
import com.example.demo.model.StudentModel;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private StudentService studentService;
    private StudentRepository studentRepository;


    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }


    @GetMapping("/student/all")
    public List<StudentModel> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/student/{id}/courses")
    public StudentModel getStudentCourseList(@PathVariable Integer id) {
        return studentService.getStudentCourseList(id);
    }

    @PutMapping("/student/update/{id}")
    public ResponseEntity<Object> updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        return studentService.updateStudent(student, id);
    }

    @PostMapping("/student/create")
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @DeleteMapping("student/delete/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Integer id) {
        return studentService.deleteStudent(id);
    }
}


