package com.example.adkt_androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DetailStudentActivity extends AppCompatActivity {

    TextView tvStuId, tvStuName;
    RecyclerView rvDefaultStudent;
    DetailStudentAdapter detailStudentAdapter;
    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_student);

        tvStuId = findViewById(R.id.tvStuId);
        tvStuName = findViewById(R.id.tvStuName);
        rvDefaultStudent = findViewById(R.id.rvDefaultStudent);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvDefaultStudent.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();
        addList();
        detailStudentAdapter = new DetailStudentAdapter(list);
        rvDefaultStudent.setAdapter(detailStudentAdapter);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        String str = (String) bundle.get("details_student");

        tvStuId.setText(str);
        tvStuName.setText(str);

    }

    private void addList() {
        list.add("True");
        list.add("True");
        list.add("False");
        list.add("False");
        list.add("True");
        list.add("False");
        list.add("True");
    }
}