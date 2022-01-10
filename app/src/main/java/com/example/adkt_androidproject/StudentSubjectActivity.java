package com.example.adkt_androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StudentSubjectActivity extends AppCompatActivity {
    RecyclerView rvStudentSubject;
    StudentSubjectAdapter studentSubjectAdapter;
    List<String> listStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_subject);

        rvStudentSubject = findViewById(R.id.rvSubjectStudent);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvStudentSubject.setLayoutManager(linearLayoutManager);
        listStr = new ArrayList<>();
        addList();
        studentSubjectAdapter = new StudentSubjectAdapter(listStr);
        rvStudentSubject.setAdapter(studentSubjectAdapter);
    }

    public void addList() {
        listStr.add("A");
        listStr.add("S");
    }
}