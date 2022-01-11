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
import com.example.lib.Models.EnrollmentModel;
import com.example.lib.Repository.ITeacherRepository;
import com.example.lib.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentActivity extends AppCompatActivity {

    TextView tvClassName;
    RecyclerView rvSubject;
    List<EnrollmentModel> studentList;
    StudentAdapter studentAdapter;
    Button bAtten,bReturn;
    String classId,teacherId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_subject);

        bReturn = findViewById(R.id.bReturn);
        rvSubject = findViewById(R.id.rvSubject);
        bAtten = findViewById(R.id.bAtten);

        // lay ma lop
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        classId = (String) bundle.get("object_subject");

        teacherId = (String)bundle.get("teacherid");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvSubject.setLayoutManager(linearLayoutManager);
        studentList = new ArrayList<>();

        // call api lay danh sach enrollment
        ITeacherRepository teacherRepository = RetrofitClient.getRetrofit().create(ITeacherRepository.class);
        Call<List<EnrollmentModel>> call = teacherRepository.GetEnrollmentListByClassId(classId);
        call.enqueue(new Callback<List<EnrollmentModel>>() {
            @Override
            public void onResponse(Call<List<EnrollmentModel>> call, Response<List<EnrollmentModel>> response) {
                List<EnrollmentModel> enrollList = response.body();

                studentAdapter = new StudentAdapter(enrollList, new IClickItemListener() {
                    @Override
                    public void onClickItem(String str) {

                    }

                    @Override
                    public void onClickEnrollment(EnrollmentModel model) {
                        onClickGoToDetail(model);
                    }
                });
                rvSubject.setAdapter(studentAdapter);
            }

            @Override
            public void onFailure(Call<List<EnrollmentModel>> call, Throwable t) {

            }
        });


        bReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnForTeacherActivity();
            }
        });

        bAtten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAttendanceActivity(classId);
            }
        });
    }

    private void onClickGoToDetail(EnrollmentModel model) {
        Intent intent = new Intent(StudentActivity.this, DetailStudentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("enrollmentId", model.enrollmentId);
        bundle.putSerializable("studentId", model.studentId);
        bundle.putSerializable("studentName", model.studentName);
        bundle.putSerializable("classId", classId);
        bundle.putSerializable("teacherid",teacherId);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private void startAttendanceActivity(String classId) {
        Intent intent = new Intent(this, AttendanceActivity.class);
        intent.putExtra("classid",classId);
        intent.putExtra("teacherid",teacherId);
        startActivity(intent);
    }
    private void returnForTeacherActivity()
    {
        Intent intent = new Intent(StudentActivity.this, ForTeacherActivity.class);
        intent.putExtra("teacherid",teacherId);
        startActivity(intent);
    }
}