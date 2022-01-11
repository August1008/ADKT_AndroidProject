package com.example.adkt_androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lib.Models.ResultModels.AttendanceResultModel;
import com.example.lib.Models.StudentModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DetailAttendanceActivity extends AppCompatActivity {

    TextView tvDateTime;
    RecyclerView rvDetailAttendance;
    List<StudentModel> list;
    DetailAttendanceAdapter detailAttendanceAdapter;
    Button bReturn, bSave;
    String classId,teacherId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_attendance);

        classId = getIntent().getStringExtra("classId");
        teacherId = getIntent().getStringExtra("teacherid");
        Bundle bundle = getIntent().getExtras();
        AttendanceResultModel result = (AttendanceResultModel) bundle.getSerializable("studentList");

        bReturn = findViewById(R.id.bReturn);
        bReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnActivity(classId);
            }
        });

        tvDateTime = findViewById(R.id.tvDateTime);
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
        tvDateTime.setText(dateTimeFormatter.format(localDateTime));

        rvDetailAttendance = findViewById(R.id.rvDetailAttendance);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvDetailAttendance.setLayoutManager(linearLayoutManager);

        detailAttendanceAdapter = new DetailAttendanceAdapter(result.studentList);
        rvDetailAttendance.setAdapter(detailAttendanceAdapter);

    }

    
    private void returnActivity(String classId)
    {
        Intent intent = new Intent(DetailAttendanceActivity.this,StudentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("object_subject",classId);
        bundle.putString("teacherid",teacherId);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtras(bundle);
        startActivity(intent);
        
    }
}