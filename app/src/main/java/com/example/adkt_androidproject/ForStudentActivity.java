package com.example.adkt_androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ForStudentActivity extends AppCompatActivity {
    CardView cvSubject,cvInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_student);
        cvSubject = findViewById(R.id.cvSubject);
        cvSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSubject();
            }
        });
        cvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInfo();
            }
        });

    }

    private void openInfo() {
        Intent intent = new Intent(ForStudentActivity.this, StudentInfoActivity.class);
        startActivity(intent);
    }

    private void openSubject() {
        Intent intent = new Intent(ForStudentActivity.this, StudentSubjectActivity.class);
        startActivity(intent);
    }
}