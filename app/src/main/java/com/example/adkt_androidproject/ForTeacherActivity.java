package com.example.adkt_androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adkt_androidproject.Interfaces.IClickItemListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ForTeacherActivity extends AppCompatActivity {

    TextView tvDatePicker;
    RecyclerView rvSubjectTeacher;
    SubjectForTeacherAdapter subjectForTeacherAdapter;
    List<String> listStr;
    CardView cvNew, cvHistory, cvContact, cvInfo;
    ImageButton ibLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_teacher);

        tvDatePicker = findViewById(R.id.tvDatePicker);
        rvSubjectTeacher = findViewById(R.id.rvSubjectTeacher);
        cvNew = findViewById(R.id.cvNew);
        cvHistory = findViewById(R.id.cvHistory);
        ibLogout = findViewById(R.id.ibLogout);
        cvInfo = findViewById(R.id.cvInfo);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvSubjectTeacher.setLayoutManager(linearLayoutManager);
        listStr = new ArrayList<>();
        addList();
        subjectForTeacherAdapter = new SubjectForTeacherAdapter(listStr, new IClickItemListener() {
            @Override
            public void onClickItem(String str) {
                onClickGoToDetail(str);
            }
        });
        rvSubjectTeacher.setAdapter(subjectForTeacherAdapter);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        tvDatePicker.setText(dtf.format(LocalDateTime.now()) + "\nDanh sách lớp học");

        cvNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity();
            }
        });

        cvHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startHistoryActivity();
            }
        });

        ibLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity();
            }
        });

        cvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startInfoTeacherActivity();
            }
        });
    }

    /*private void setbDatePicker() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                tvDatePicker.setText(date);

            }
        }, year, month, day);
        datePickerDialog.show();
    }*/

    public void addList() {
        listStr.add("19DTHA1");
        listStr.add("19DTHA2");
        listStr.add("19DTHA3");
        listStr.add("19DTHA4");
        listStr.add("19DTHA5");
        listStr.add("19DTHA6");
        listStr.add("19DTHA7");
        listStr.add("19DTHA8");
        listStr.add("19DTHA9");
        listStr.add("19DTHA10");
    }

    private void startNewActivity() {
        Intent intent = new Intent(this, AddNewActivity.class);
        startActivity(intent);
    }

    private void startHistoryActivity() {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void onClickGoToDetail(String str) {
        Intent intent = new Intent(this, DetailSubjectActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_subject", str);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void startInfoTeacherActivity() {
        Intent intent = new Intent(this, InfoTeacherActivity.class);
        startActivity(intent);
    }
}