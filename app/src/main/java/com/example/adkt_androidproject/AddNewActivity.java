package com.example.adkt_androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.lib.Models.InsertModel.ClassInsertModel;
import com.example.lib.Models.ResultModels.AddClassResultModel;
import com.example.lib.Repository.ITeacherRepository;
import com.example.lib.RetrofitClient;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddNewActivity extends AppCompatActivity {

    TextView tvDateStart, tvDateEnd, tvTime,etSubject;
    Button btnCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        String teacherId = getIntent().getStringExtra("teacherId");

        tvDateStart = findViewById(R.id.tvDateStart);
        tvDateEnd = findViewById(R.id.tvDateEnd);
        tvTime = findViewById(R.id.tvTime);
        btnCreate = findViewById(R.id.btnCreate);
        etSubject = findViewById(R.id.etSubject);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // tao 1 lop hoc moi
                ClassInsertModel newClass = new ClassInsertModel(
                        etSubject.getText().toString(),
                        tvDateStart.getText().toString(),
                        tvDateEnd.getText().toString(),
                        teacherId);

                ITeacherRepository teacherRepository = RetrofitClient.getRetrofit().create(ITeacherRepository.class);
                Call<AddClassResultModel> call = teacherRepository.AddNewClass(newClass);
                call.enqueue(new Callback<AddClassResultModel>() {
                    @Override
                    public void onResponse(Call<AddClassResultModel> call, Response<AddClassResultModel> response) {
                        Toast.makeText(AddNewActivity.this,response.body().message,Toast.LENGTH_SHORT).show();
                        openForTeacherActivity(teacherId);
                    }

                    @Override
                    public void onFailure(Call<AddClassResultModel> call, Throwable t) {
                        Toast.makeText(AddNewActivity.this,"Create class fail",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        tvDateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setbDatePicker(false);
            }
        });
        tvDateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setbDatePicker(true);
            }
        });
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimePicker();
            }
        });
    }

    private void setbDatePicker(boolean flag) {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                if (flag == false) {
                    tvDateStart.setText(date);
                } else {
                    tvDateEnd.setText(date);
                }


            }
        }, year, month, day);
        datePickerDialog.show();
    }
    int hour, minutes;
    private void setTimePicker() {

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hour = hourOfDay;
                minutes = minute;
                tvTime.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minutes));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minutes, true);
        timePickerDialog.setTitle("Chọn giờ bắt đầu");
        timePickerDialog.show();
    }
    private void openForTeacherActivity(String teacherId)
    {
        Intent intent = new Intent(AddNewActivity.this,ForTeacherActivity.class);
        intent.putExtra("teacherId",teacherId);
        startActivity(intent);
    }
}