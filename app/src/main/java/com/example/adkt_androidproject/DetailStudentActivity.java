package com.example.adkt_androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.lib.Models.AttendanceModel;
import com.example.lib.Repository.ITeacherRepository;
import com.example.lib.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        String enrollmentId = (String) bundle.get("enrollmentId");
        String classId = (String)bundle.get("classId");
        String studentId = (String) bundle.get("studentId");
        String studentName = (String) bundle.get("studentName");
        tvStuId.setText(studentId);
        tvStuName.setText(studentName);


        rvDefaultStudent = findViewById(R.id.rvDefaultStudent);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvDefaultStudent.setLayoutManager(linearLayoutManager);


        list = new ArrayList<>();


        // call api lay danh sach diem danh
        ITeacherRepository teacherRepository = RetrofitClient.getRetrofit().create(ITeacherRepository.class);
        Call<List<AttendanceModel>> call = teacherRepository.GetAttendanceByEnrollmentId(Integer.parseInt(enrollmentId));
        call.enqueue(new Callback<List<AttendanceModel>>() {
            @Override
            public void onResponse(Call<List<AttendanceModel>> call, Response<List<AttendanceModel>> response) {
                List<AttendanceModel> aList = response.body();
                for(AttendanceModel a : aList)
                {
                    list.add(a.GetStatus());
                }
                detailStudentAdapter = new DetailStudentAdapter(list);
                rvDefaultStudent.setAdapter(detailStudentAdapter);
            }

            @Override
            public void onFailure(Call<List<AttendanceModel>> call, Throwable t) {

            }
        });

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