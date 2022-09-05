package com.example.demo.service;

import com.example.demo.Student;
import com.example.demo.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student getById(Integer studentId) {
        return studentDao.getById(studentId);
    }

    @Override
    public void postByStudent(Student student) {
        studentDao.postByStudent(student);
    }

    @Override
    public void postByStudentlist(List<Student> studentList) {
        studentDao.postByStudentlist(studentList);
    }

    @Override
    public void putByStudent(Student student) {
        studentDao.putByStudent(student);
    }

    @Override
    public void deleteById(Integer studentId) {
        studentDao.deleteById(studentId);
    }
}
