package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.model.CourseModel;
import com.example.demo.model.StudentModel;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;
    private CourseRepository courseRepository;

    public CourseController(CourseService courseService, CourseRepository courseRepository) {
        this.courseService = courseService;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/courses/all")
    public List<CourseModel> getCourses()
    {
        return courseService.getCourses();
    }

    @GetMapping("/courses/{id}")
    public Optional<Course> findById(@PathVariable("id") Integer id){
        return courseService.findById(id);
    }


    @GetMapping("/courses/{id}/students")
    public CourseModel getCourseEnrolledStudents(@PathVariable Integer id) {
        return courseService.getCourseEnrolledStudents(id);
    }

    @PostMapping("/courses/create")
    public ResponseEntity<Object> createCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @DeleteMapping("/courses/delete/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable Integer id) {
        return courseService.deleteCourse(id);
    }

    @PutMapping("/courses/update/{id}")
    public ResponseEntity<Object> updateCourse(@PathVariable Integer id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }
}
