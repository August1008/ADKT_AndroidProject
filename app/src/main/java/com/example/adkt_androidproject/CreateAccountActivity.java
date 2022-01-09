package com.example.adkt_androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.lib.Models.InsertModel.StudentInsertModel;
import com.example.lib.Models.StudentModel;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class CreateAccountActivity extends AppCompatActivity {

    EditText etDate,etUsername,etPassword,etEmail,etReenterPassword,etFullname,etStudentId;
    Button bNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        etStudentId = findViewById(R.id.etStudentId);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        etReenterPassword = findViewById(R.id.etReenterPassword);
        etFullname = findViewById(R.id.etFullname);
        etDate = findViewById(R.id.etDate);
        bNext = findViewById(R.id.bNext);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(calendar.YEAR);
        final int month = calendar.get(calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateAccountActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        etDate.setText(date);
                    }
                },year, month, day);
                datePickerDialog.show();
            }
        });

        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // xu ly nhập sai mật khẩu re-enter
                // xu lý chưa điền đầy đủ thông tin

                // Create a new student
                StudentInsertModel newStudent = new StudentInsertModel(
                        etUsername.getText().toString(),
                        etPassword.getText().toString(),
                        StudentModel.STUDENT,
                        etStudentId.getText().toString(),
                        etFullname.getText().toString(),
                        etDate.getText().toString(),
                        etEmail.getText().toString()
                );
                openAddImageStudent(newStudent);
            }
        });
    }

    private void openAddImageStudent(StudentInsertModel newStudent) {
        Intent intent = new Intent(CreateAccountActivity.this, AddImageStudentActivity.class);
        intent.putExtra("newStudent",newStudent);
        startActivity(intent);
    }
}