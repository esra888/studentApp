package com.example.demo.model;

import java.util.List;

public class CourseModel {

    private String name;
    private List<StudentModel> students;


    public List<StudentModel> getStudents() {
        return students;
    }

    public void setStudents(List<StudentModel> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
