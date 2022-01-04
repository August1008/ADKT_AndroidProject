package com.example.adkt_androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ForTeacherActivity extends AppCompatActivity {

    Button bDatePicker;
    RecyclerView rvSubjectTeacher;
    SubjectForTeacherAdapter subjectForTeacherAdapter;
    List<String> listStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_teacher);

        bDatePicker = findViewById(R.id.bDatePicker);
        rvSubjectTeacher = findViewById(R.id.rvSubjectTeacher);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvSubjectTeacher.setLayoutManager(linearLayoutManager);
        listStr = new ArrayList<>();
        addList();
        subjectForTeacherAdapter = new SubjectForTeacherAdapter(listStr);
        rvSubjectTeacher.setAdapter(subjectForTeacherAdapter);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        bDatePicker.setText(dtf.format(LocalDateTime.now()));

        bDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setbDatePicker();
            }
        });
    }

    private void setbDatePicker() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                bDatePicker.setText(date);

            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void addList() {
        listStr.add("A");
        listStr.add("S");
        listStr.add("D");
        listStr.add("F");
        listStr.add("G");
        listStr.add("H");
        listStr.add("J");
        listStr.add("K");
        listStr.add("L");
        listStr.add("P");
        listStr.add("L");
        listStr.add("P");
    }
}