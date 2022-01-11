package com.example.adkt_androidproject;
import static com.example.lib.RetrofitClient.getRetrofit;
import static com.example.lib.RetrofitClient.getRetrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lib.Models.LoginUserModel;
import com.example.lib.Models.ResultModels.StudentLoginResultModel;
import com.example.lib.Models.ResultModels.TeacherLoginResultModel;
import com.example.lib.Models.StudentModel;
import com.example.lib.Models.TeacherModel;
import com.example.lib.Models.UserModel;
import com.example.lib.Repository.ITeacherRepository;
import com.example.lib.Repository.IUserRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.Toast;

import com.example.lib.Models.LoginUserModel;
import com.example.lib.Models.StudentModel;
import com.example.lib.Models.UserModel;
import com.example.lib.Repository.IUserRepository;
import com.example.lib.RetrofitClient;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    EditText etUsername,etPassword;
    TextView tvCreateAccount;
    RadioButton rbForTeacher,rbForStudent;
    Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCreateAccount = findViewById(R.id.tvCreateAccount);
        rbForTeacher = findViewById(R.id.rbForTeacher);
        rbForStudent = findViewById(R.id.rbForStudent);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        bLogin = findViewById(R.id.bLogin);

        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateAccount();
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IUserRepository userRepository = getRetrofit().create(IUserRepository.class);

                LoginUserModel loginUserModel = new LoginUserModel();
                loginUserModel.Username = etUsername.getText().toString();
                loginUserModel.Password = etPassword.getText().toString();
                if (rbForTeacher.isChecked()) {

                    loginUserModel.UserType = UserModel.TEACHER;
                    Call<TeacherLoginResultModel> call = userRepository.LoginTeacher(loginUserModel);
                    call.enqueue(new Callback<TeacherLoginResultModel>() {
                        @Override
                        public void onResponse(Call<TeacherLoginResultModel> call, Response<TeacherLoginResultModel> response) {
                            TeacherLoginResultModel result = response.body();
                            if(result.message.equals("fail"))
                                Toast.makeText(MainActivity.this,"Login fail",Toast.LENGTH_SHORT).show();
                            else
                            {
                                Toast.makeText(MainActivity.this,result.teacher.name,Toast.LENGTH_SHORT).show();
                                openForTeacher(result.teacher.teacherId);
                            }
                        }

                        @Override
                        public void onFailure(Call<TeacherLoginResultModel> call, Throwable t) {

                        }
                    });

                } else if (rbForStudent.isChecked()){
                    loginUserModel.UserType = UserModel.STUDENT;
                    Call<StudentLoginResultModel> call = userRepository.Login(loginUserModel);
                    call.enqueue(new Callback<StudentLoginResultModel>() {
                        @Override
                        public void onResponse(Call<StudentLoginResultModel> call, Response<StudentLoginResultModel> response) {
                            StudentLoginResultModel result = response.body();
                            if(result.message.equals("fail"))
                                Toast.makeText(MainActivity.this,"Login fail",Toast.LENGTH_SHORT).show();
                            else
                            {

                                Toast.makeText(MainActivity.this,result.student.name,Toast.LENGTH_SHORT).show();
                                openForStudent(result.student.studentId);
                            }
                        }

                        @Override
                        public void onFailure(Call<StudentLoginResultModel> call, Throwable t) {

                        }
                    });
                }
            }
        });

    }

    private void openCreateAccount() {
        Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
        startActivity(intent);
    }

    private void openForTeacher(String teacherId){
        Intent intent = new Intent(MainActivity.this, ForTeacherActivity.class);
        intent.putExtra("teacherId",teacherId);
        startActivity(intent);
    }
    private void openForStudent(String studentId){
        Intent intent = new Intent(MainActivity.this, ForStudentActivity.class);
        intent.putExtra("studentId",studentId);
        startActivity(intent);
    }
}