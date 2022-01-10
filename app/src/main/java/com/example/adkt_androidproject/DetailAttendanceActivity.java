package com.example.adkt_androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DetailAttendanceActivity extends AppCompatActivity {

    TextView tvDateTime;
    RecyclerView rvDetailAttendance;
    List<String> list;
    DetailAttendanceAdapter detailAttendanceAdapter;
    Button bReturn, bSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_attendance);

        bReturn = findViewById(R.id.bReturn);
        bSave = findViewById(R.id.bSave);

        tvDateTime = findViewById(R.id.tvDateTime);
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
        tvDateTime.setText(dateTimeFormatter.format(localDateTime));

        rvDetailAttendance = findViewById(R.id.rvDetailAttendance);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvDetailAttendance.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();
        addList();
        detailAttendanceAdapter = new DetailAttendanceAdapter(list);
        rvDetailAttendance.setAdapter(detailAttendanceAdapter);
    }

    private void addList() {
        list.add("True");
        list.add("False");
        list.add("True");
        list.add("False");
        list.add("True");
        list.add("False");
        list.add("True");
    }
}