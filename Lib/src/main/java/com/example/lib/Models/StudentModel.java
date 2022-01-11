package com.example.lib.Models;

import java.io.Serializable;

public class StudentModel extends UserModel implements Serializable {
    public String studentId;
    public String name;
    public String birthDay;
    public String email;
    public String userId;
    public String personId;

    public StudentModel(String studentId, String name, String birthDay, String email, String userId, String personId) {
        this.studentId = studentId;
        this.name = name;
        this.birthDay = birthDay;
        this.email = email;
        this.userId = userId;
        this.personId = personId;
    }

}
