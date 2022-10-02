package com.example.student.service;

import com.example.student.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findByMaximumMarks(int totalMarks);
    Student saveOrUpdateStudent(Student student);
    void deleteStudentById(String id);
    Student findByStudentNumber(long studentNumber);
    List<Student> findByYear(int yearOfStudying);
}
