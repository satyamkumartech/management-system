package com.example.student.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
public class Student {
    @Id
    private String id;
    private int studentNumber;
    private String name;
    private String gender;
    private int totalMarks;
    private String passStatus;
    private int yearOfStudying;
    private int physicsTheory;
    private int PhysicsPractical;
    private int chemistryTheory;
    private int chemistryPractical;
    private int maths;
    private int english;

    public enum gender {
        M, F;
    }
    public enum yearOfStudying {
        FIRST, SECOND, THIRD, FOURTH;
    }

    public Student() {

    }

    public Student(int studentNumber, String name, String gender, int totalMarks, String passStatus, int yearOfStudying, int physicsTheory, int physicsPractical, int chemistryTheory, int chemistryPractical, int maths, int english) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.gender = gender;
        this.totalMarks = totalMarks;
        this.passStatus = passStatus;
        this.yearOfStudying = yearOfStudying;
        this.physicsTheory = physicsTheory;
        PhysicsPractical = physicsPractical;
        this.chemistryTheory = chemistryTheory;
        this.chemistryPractical = chemistryPractical;
        this.maths = maths;
        this.english = english;
    }
}
