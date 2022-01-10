package com.example.adkt_androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddNewActivity extends AppCompatActivity {

    TextView tvDateStart, tvDateEnd, tvTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        tvDateStart = findViewById(R.id.tvDateStart);
        tvDateEnd = findViewById(R.id.tvDateEnd);
        tvTime = findViewById(R.id.tvTime);

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
}