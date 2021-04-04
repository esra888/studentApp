package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.model.CourseModel;
import com.example.demo.model.StudentModel;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;

    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

   /** Get All courses */
    public List<CourseModel> getCourses() {
        List<Course> courseList = (List<Course>) courseRepository.findAll();
        if(courseList.size()>0) {
            List<CourseModel> courseModels = new ArrayList<>();
            for (Course course : courseList) {
                CourseModel model = new CourseModel();
                model.setName(course.getName());
                model.setStudents(getStudentList(course));
                courseModels.add(model);
            }
            return courseModels;
        } else return new ArrayList<CourseModel>();
    }


    public Optional<Course> findById(Integer id) {
        return courseRepository.findById(id);
    }

    /** Create a new course */
    @Transactional
    public ResponseEntity<Object> addCourse(Course model) {
        Course newCourse = new Course();
        newCourse.setName(model.getName());
        newCourse.setStudents(model.getStudents());

        Course savedCourse = courseRepository.save(newCourse);

        if (courseRepository.findById(savedCourse.getId()).isPresent()) {
            return ResponseEntity.ok("Course Created Successfully");
        }
        else return ResponseEntity.unprocessableEntity().body("Failed Creating User as Specified");

    }



    /**Delete a specified course given the id*/
    public ResponseEntity<Object> deleteCourse(Integer id) {
        if(courseRepository.findById(id).isPresent()){
                courseRepository.deleteById(id);
                if (courseRepository.findById(id).isPresent()) {
                    return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
                } else return ResponseEntity.ok().body("Successfully deleted specified record");
        } else
            return ResponseEntity.unprocessableEntity().body("No Records Found");
    }


    /** Update a Course */
    public ResponseEntity<Object> updateCourse(Integer id, Course course) {
        if(courseRepository.findById(id).isPresent()){
            Course newCourse = courseRepository.findById(id).get();
            newCourse.setName(course.getName());
            Course savedCourse = courseRepository.save(newCourse);
            if(courseRepository.findById(savedCourse.getId()).isPresent())
                return ResponseEntity.accepted().body("Role saved successfully");
            else return ResponseEntity.badRequest().body("Failed to update Role");

        } else return ResponseEntity.unprocessableEntity().body("Specified Role not found");
    }

    /* for reaching studentList that courses have */
    public List<StudentModel> getStudentList(Course course){

        List<StudentModel> studentList = new ArrayList<>();
        for(int i=0; i< course.getStudents().size(); i++) {
            StudentModel studentModel = new StudentModel();
            studentModel.setName(course.getStudents().get(i).getName());
            studentList.add(studentModel);
        }
        return studentList;
    }

    /* get studentList that specific course have */
    public CourseModel getCourseEnrolledStudents(Integer id){

        if(courseRepository.findById(id).isPresent())
        {
            Course course = courseRepository.findById(id).get();
            CourseModel courseModel = new CourseModel();
            courseModel.setName(course.getName());
            courseModel.setStudents( getStudentList(course));
            return courseModel;
        }
        else return null;
    }
}






