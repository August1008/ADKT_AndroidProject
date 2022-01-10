package com.example.adkt_androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ForStudentActivity extends AppCompatActivity {

    ImageButton ibLog_out;
    ClassAdapter classAdapter;
    RecyclerView rvClass;
    List<String> list;
    FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_student);

        rvClass = findViewById(R.id.rvClass);
        ibLog_out = findViewById(R.id.ibLog_out);
        fabAdd = findViewById(R.id.fabAdd);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvClass.setLayoutManager(linearLayoutManager);
        list = new ArrayList<>();
        addList();
        classAdapter = new ClassAdapter(list);
        rvClass.setAdapter(classAdapter);
        ibLog_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity();
            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAddClassActivity();
            }
        });
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private void startAddClassActivity() {
        Intent intent = new Intent(this, JoinClassActivity.class);
        startActivity(intent);
    }

    public void addList() {
        list.add("19DTHA1");
        list.add("19DTHA2");
        list.add("19DTHA5");
        list.add("19DTHA6");
        list.add("19DTHA8");
        list.add("19DTHA10");
    }
}