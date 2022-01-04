package com.example.adkt_androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvCreateAccount;
    RadioButton rbForTeacher;
    Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCreateAccount = findViewById(R.id.tvCreateAccount);
        rbForTeacher = findViewById(R.id.rbForTeacher);
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
                    openForStudent();
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