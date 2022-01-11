package com.example.lib.Models;

public class ClassModel {
    public String classId;
    public String subject;
    public String teacherName;
    public String startDate;
    public String endDate;

    public ClassModel(String subject, String teacherName) {
        this.subject = subject;
        this.teacherName = teacherName;
    }
}
