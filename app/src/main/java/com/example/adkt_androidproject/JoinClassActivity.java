package com.example.adkt_androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lib.Models.ResultModels.AddEnrollmentResult;
import com.example.lib.Repository.StudentRepository;
import com.example.lib.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinClassActivity extends AppCompatActivity {

    EditText etClassCode;
    Button bJoin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_class);

        etClassCode = findViewById(R.id.etClassCode);
        bJoin = findViewById(R.id.bJoin);

        String studentId = getIntent().getStringExtra("studentId");
        String studentName = getIntent().getStringExtra("name");
        bJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentRepository studentRepository = RetrofitClient.getRetrofit().create(StudentRepository.class);
                Call<AddEnrollmentResult> call = studentRepository.EnrollClass(etClassCode.getText().toString(),studentId);
                call.enqueue(new Callback<AddEnrollmentResult>() {
                    @Override
                    public void onResponse(Call<AddEnrollmentResult> call, Response<AddEnrollmentResult> response) {
                        Toast.makeText(JoinClassActivity.this,response.body().message,Toast.LENGTH_SHORT).show();
                        openForStudentActivity(studentId,studentName);
                    }

                    @Override
                    public void onFailure(Call<AddEnrollmentResult> call, Throwable t) {
                        Toast.makeText(JoinClassActivity.this,"Join class fail",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void openForStudentActivity(String studentId, String name) {
        Intent intent = new Intent(JoinClassActivity.this,ForStudentActivity.class);
        intent.putExtra("studentId",studentId);
        intent.putExtra("name",name);
        startActivity(intent);
    }
}