package com.example.adkt_androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.adkt_androidproject.Interfaces.IClickItemListener;

import java.util.ArrayList;
import java.util.List;

public class DetailSubjectActivity extends AppCompatActivity {

    TextView tvTemp;
    RecyclerView rvSubject;
    List<String> listStr;
    SubjectAdapter subjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_subject);

        tvTemp = findViewById(R.id.tvTemp);
        rvSubject = findViewById(R.id.rvSubject);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvSubject.setLayoutManager(linearLayoutManager);
        listStr = new ArrayList<>();
        addList();
        subjectAdapter = new SubjectAdapter(listStr, new IClickItemListener() {
            @Override
            public void onClickItem(String str) {
                onClickGoToDetail(str);
            }
        });
        rvSubject.setAdapter(subjectAdapter);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        String str = (String) bundle.get("object_subject");

        tvTemp.setText(str.toString());
    }

    private void addList() {
        listStr.add("Cơ sở dữ liệu nâng cao");
        listStr.add("Cơ sở dữ liệu và hệ quản trị cơ sở dữ liệu");
    }
    private void onClickGoToDetail(String str) {
        Intent intent = new Intent(this, AttendanceActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("details_subject", str);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}