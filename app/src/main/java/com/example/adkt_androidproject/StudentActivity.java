package com.example.adkt_androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adkt_androidproject.Interfaces.IClickItemListener;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity {

    TextView tvClassName;
    RecyclerView rvSubject;
    List<String> listStr;
    StudentAdapter studentAdapter;
    Button bAtten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_subject);

        tvClassName = findViewById(R.id.tvClassName);
        rvSubject = findViewById(R.id.rvSubject);
        bAtten = findViewById(R.id.bAtten);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvSubject.setLayoutManager(linearLayoutManager);
        listStr = new ArrayList<>();
        addList();
        studentAdapter = new StudentAdapter(listStr, new IClickItemListener() {
            @Override
            public void onClickItem(String str) {
                onClickGoToDetail(str);
            }
        });
        rvSubject.setAdapter(studentAdapter);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        String str = (String) bundle.get("object_subject");

        tvClassName.setText(str);

        bAtten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAttendanceActivity();
            }
        });
    }

    private void addList() {
        listStr.add("Nguyễn Bình An");
        listStr.add("Phan Văn Đức");
        listStr.add("Nguyễn Trung Kiên");
        listStr.add("Tô Vĩnh Thái");
    }
    private void onClickGoToDetail(String str) {
        Intent intent = new Intent(this, DetailStudentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("details_student", str);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private void startAttendanceActivity() {
        Intent intent = new Intent(this, AttendanceActivity.class);
        startActivity(intent);
    }
}