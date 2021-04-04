package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.model.CourseModel;
import com.example.demo.model.StudentModel;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    /** Create a new Student */
    public ResponseEntity<Object> createStudent(Student model) {
            Student student = new Student();
            student.setName(model.getName());
            student.setCourses(model.getCourses());

            Student savedStudent = studentRepository.save(student);
            if (studentRepository.findById(savedStudent.getId()).isPresent()) {
                return ResponseEntity.ok("User Created Successfully");
            }
            else return ResponseEntity.unprocessableEntity().body("Failed Creating User as Specified");
    }


    /** Update an Existing Student */
    @Transactional
    public ResponseEntity<Object> updateStudent(Student student, Integer id) {
        if(studentRepository.findById(id).isPresent()) {
            Student newStudent = studentRepository.findById(id).get();
            newStudent.setName(student.getName());
            newStudent.setCourses(student.getCourses());
            Student savedStudent = studentRepository.save(newStudent);
            if(studentRepository.findById(savedStudent.getId()).isPresent())
                return  ResponseEntity.accepted().body("User updated successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed updating the user specified");
        } else return ResponseEntity.unprocessableEntity().body("Cannot find the user specified");
    }

    /** Delete a Student*/
    public ResponseEntity<Object> deleteStudent(Integer id) {
        if (studentRepository.findById(id).isPresent()) {
            studentRepository.deleteById(id);
            if (studentRepository.findById(id).isPresent())
                return ResponseEntity.unprocessableEntity().body("Failed to Delete the specified User");
            else return ResponseEntity.ok().body("Successfully deleted the specified user");
        } else return ResponseEntity.badRequest().body("Cannot find the user specified");
    }


    /* Get All students */
    public List<StudentModel> getStudents() {
        List<Student> studentList = (List<Student>) studentRepository.findAll();
        if(studentList.size()>0) {
            List<StudentModel> studentModels = new ArrayList<>();
            for (Student student : studentList) {
                StudentModel model = new StudentModel();
                model.setName(student.getName());
                model.setCourses(getCourseList(student));
                studentModels.add(model);
            }
            return studentModels;
        } else return new ArrayList<StudentModel>();
    }

    /* for reaching courseList that students have */
    public List<CourseModel> getCourseList(Student student){

        List<CourseModel> courseList = new ArrayList<>();
        for(int i=0; i< student.getCourses().size(); i++) {
            CourseModel courseModel = new CourseModel();
            courseModel.setName(student.getCourses().get(i).getName());
            courseList.add(courseModel);
        }
        return courseList;
    }

    /* get courseList that specific student have */
    public StudentModel getStudentCourseList(Integer id){
        if(studentRepository.findById(id).isPresent())
        {
            Student student = studentRepository.findById(id).get();
            StudentModel studentModel = new StudentModel();
            studentModel.setName(student.getName());
            studentModel.setCourses( getCourseList(student));
            return studentModel;
        }
        else return null;
    }

}
