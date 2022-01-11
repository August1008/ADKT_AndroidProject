package com.example.adkt_androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lib.Models.Const;
import com.example.lib.Models.ResultModels.AttendanceResultModel;
import com.example.lib.Models.StudentModel;
import com.example.lib.RealPathUtil;
import com.example.lib.Repository.ITeacherRepository;
import com.example.lib.RetrofitClient;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.File;
import java.io.IOException;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceActivity extends AppCompatActivity {

    Button bAddPicture, bAction;
    ImageView ivPicture;
    Uri classUri;
    String classId,teacherId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        classId = getIntent().getStringExtra("classid");
        teacherId = getIntent().getStringExtra("teacherid");
        bAddPicture = findViewById(R.id.bAddPicture);
        ivPicture = findViewById(R.id.ivPicture);
        bAction = findViewById(R.id.bAction);

        bAddPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(AttendanceActivity.this, "Alola", Toast.LENGTH_SHORT).show();
                requestPermission();
           }
        });
        ivPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });
        bAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestBody requestBodyClassId = RequestBody.create(MediaType.parse("multipart/form-data"),classId);

                File classImage = new File(RealPathUtil.getRealPath(AttendanceActivity.this,classUri));
                RequestBody requestBodyClassImage = RequestBody.create(MediaType.parse("multipart/form-data"),classImage);
                MultipartBody.Part multipartBodyimage1 = MultipartBody.Part.createFormData(Const.KEY_classIMAGE,classImage.getName(),requestBodyClassImage);

                ITeacherRepository teacherRepository = RetrofitClient.getRetrofit().create(ITeacherRepository.class);
                Call<List<StudentModel>> call = teacherRepository.SaveAttendances(multipartBodyimage1,requestBodyClassId);

                call.enqueue(new Callback<List<StudentModel>>() {
                    @Override
                    public void onResponse(Call<List<StudentModel>> call, Response<List<StudentModel>> response) {
                        AttendanceResultModel result = new AttendanceResultModel();
                        result.studentList = response.body();
                        Toast.makeText(AttendanceActivity.this,"successfully",Toast.LENGTH_SHORT).show();
                        startDetailAttendanceActivity(result,classId);
                    }

                    @Override
                    public void onFailure(Call<List<StudentModel>> call, Throwable t) {

                    }
                });

            }
        });
    }

    private void requestPermission() {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openBottomPicker();
                //Toast.makeText(AttendanceActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(AttendanceActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.create()
                .setPermissionListener(permissionListener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();
    }
    private void openBottomPicker() {
        TedBottomPicker.with(AttendanceActivity.this)
                .show(new TedBottomSheetDialogFragment.OnImageSelectedListener() {
                    @Override
                    public void onImageSelected(Uri uri) {
                        try {
                            classUri = uri;
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            ivPicture.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    public void startDetailAttendanceActivity(AttendanceResultModel resultModel,String classId) {
        Intent intent = new Intent(this, DetailAttendanceActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("studentList",resultModel);
        intent.putExtras(bundle);
        intent.putExtra("classId",classId);
        intent.putExtra("teacherid",teacherId);
        startActivity(intent);
    }
}