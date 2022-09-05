package com.example.demo.service;

import com.example.demo.Student;

import java.util.List;

public interface StudentService {
    Student getById(Integer studentId);

    void postByStudent(Student student);

    void postByStudentlist(List<Student> studentList);

    void putByStudent(Student student);

    void deleteById(Integer studentId);
}
