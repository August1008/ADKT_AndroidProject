package com.example.adkt_androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.adkt_androidproject.Interfaces.IClickItemListener;
import com.example.lib.Models.ClassModel;
import com.example.lib.Models.EnrollmentModel;
import com.example.lib.Repository.ITeacherRepository;
import com.example.lib.RetrofitClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForTeacherActivity extends AppCompatActivity {

    TextView tvDatePicker;
    RecyclerView rvSubjectTeacher;
    ClassForTeacherAdapter classForTeacherAdapter;
    List<ClassModel> classList;
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
        classList = new ArrayList<ClassModel>();

        // lay teacherId tu giao dien dang nhap
        String teacherId = getIntent().getStringExtra("teacherId");
        ITeacherRepository teacherRepository = RetrofitClient.getRetrofit().create(ITeacherRepository.class);
        Call<List<ClassModel>> call = teacherRepository.GetClassListByTeacherId(teacherId);
        call.enqueue(new Callback<List<ClassModel>>() {
            @Override
            public void onResponse(Call<List<ClassModel>> call, Response<List<ClassModel>> response) {
                classList = response.body();
                classForTeacherAdapter = new ClassForTeacherAdapter(classList, new IClickItemListener() {
                    @Override
                    public void onClickItem(String str) {
                        onClickGoToDetail(str);
                    }

                    @Override
                    public void onClickEnrollment(EnrollmentModel model) {

                    }
                });
                rvSubjectTeacher.setAdapter(classForTeacherAdapter);
            }

            @Override
            public void onFailure(Call<List<ClassModel>> call, Throwable t) {

            }
        });



        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        tvDatePicker.setText(dtf.format(LocalDateTime.now()) + "\nDanh sách lớp học");

        cvNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(teacherId);
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

    private void startNewActivity(String teacherId) {
        Intent intent = new Intent(this, AddNewActivity.class);
        intent.putExtra("teacherId",teacherId);
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
        Intent intent = new Intent(this, StudentActivity.class);
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