package com.example.lib.Models.InsertModel;

public class ClassInsertModel {
    public String classId;
    public String subject;
    public String startDate;
    public String endDate;
    public String teacherId;

    public ClassInsertModel(String subject, String startDate, String endDate,String teacherId) {
        this.subject = subject;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teacherId = teacherId;
    }
}
