package com.example.teacher.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Teacher {
    @Id
    private String id;
    private String loginId;
    private String password;
    private String name;
    private String tecaherId;

    public Teacher() {
    }

    public Teacher(String loginId, String password, String name, String tecaherId) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.tecaherId = tecaherId;
    }
}
