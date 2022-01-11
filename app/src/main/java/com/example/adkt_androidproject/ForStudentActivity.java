package com.example.adkt_androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.adkt_androidproject.Interfaces.IClickItemListener;
import com.example.lib.Models.EnrollmentModel;
import com.example.lib.Repository.StudentRepository;
import com.example.lib.RetrofitClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForStudentActivity extends AppCompatActivity {

    ImageButton ibLog_out;
    ClassAdapter classAdapter;
    RecyclerView rvClass;
    List<EnrollmentModel> enrollmentModelList;
    FloatingActionButton fabAdd;
    TextView tvStudentTiltle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_student);

        rvClass = findViewById(R.id.rvClass);
        ibLog_out = findViewById(R.id.ibLog_out);
        fabAdd = findViewById(R.id.fabAdd);
        tvStudentTiltle = findViewById(R.id.tvStudentTitle);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvClass.setLayoutManager(linearLayoutManager);
        String studentId = getIntent().getStringExtra("studentId");

        StudentRepository studentRepository = RetrofitClient.getRetrofit().create(StudentRepository.class);
        Call<List<EnrollmentModel>> call = studentRepository.GetEnrollmentsBystudentId(studentId);
        call.enqueue(new Callback<List<EnrollmentModel>>() {
            @Override
            public void onResponse(Call<List<EnrollmentModel>> call, Response<List<EnrollmentModel>> response) {
                enrollmentModelList = response.body();
                classAdapter = new ClassAdapter(enrollmentModelList);
                rvClass.setAdapter(classAdapter);
            }

            @Override
            public void onFailure(Call<List<EnrollmentModel>> call, Throwable t) {

            }
        });
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
    //test

}