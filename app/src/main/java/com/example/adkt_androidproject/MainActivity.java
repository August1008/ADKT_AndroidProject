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
import com.example.lib.Models.StudentModel;
import com.example.lib.Models.UserModel;
import com.example.lib.Repository.IUserRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.Toast;

import com.example.lib.Models.LoginUserModel;
import com.example.lib.Models.StudentModel;
import com.example.lib.Models.UserModel;
import com.example.lib.Repository.IUserRepository;

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
                if (rbForTeacher.isChecked()) {
                    openForTeacher();
                } else {
                    LoginUserModel loginUserModel = new LoginUserModel();
                    loginUserModel.Username = etUsername.getText().toString();
                    loginUserModel.Password = etPassword.getText().toString();
                    loginUserModel.UserType = UserModel.STUDENT;

                    IUserRepository userRepository = getRetrofit().create(IUserRepository.class);
                    Call<StudentModel> call = userRepository.Login(loginUserModel);
                    call.enqueue(new Callback<StudentModel>() {
                        @Override
                        public void onResponse(Call<StudentModel> call, Response<StudentModel> response) {
                            StudentModel studentModel = response.body();
                            if(studentModel == null)
                                Toast.makeText(null,"dang nhap that bai",Toast.LENGTH_SHORT).show();
                            else
                            {

                                Toast.makeText(MainActivity.this,studentModel.name,Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<StudentModel> call, Throwable t) {

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

    private void openForTeacher(){
        Intent intent = new Intent(MainActivity.this, ForTeacherActivity.class);
        startActivity(intent);
    }
    private void openForStudent(){
        Intent intent = new Intent(MainActivity.this, ForStudentActivity.class);
        startActivity(intent);
    }
}