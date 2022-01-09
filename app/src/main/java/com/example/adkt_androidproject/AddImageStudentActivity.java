package com.example.adkt_androidproject;

import static com.example.lib.RetrofitClient.getRetrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.lib.Models.Const;
import com.example.lib.Models.InsertModel.InsertResultModel;
import com.example.lib.Models.InsertModel.StudentInsertModel;
import com.example.lib.RealPathUtil;
import com.example.lib.Repository.StudentRepository;
import com.example.lib.RetrofitClient;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;
import gun0912.tedbottompicker.TedBottomSheetDialogFragment;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddImageStudentActivity extends AppCompatActivity {

    private Button bAddPhoto, bCreate;
    private RecyclerView rvPhoto;
    private PhotoAdapter photoAdapter;
    private List<Uri> imgList;
    private ProgressBar progressBar;
    private StudentInsertModel newStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image_student);

        // lấy thông tin sinh viên từ CreateAccountActivity
         newStudent = getIntent().getParcelableExtra("newStudent");

        progressBar = (ProgressBar)findViewById(R.id.simpleProgressBar);

        bAddPhoto = findViewById(R.id.bAddPhoto);
        rvPhoto = findViewById(R.id.rvPhoto);
        bCreate = findViewById(R.id.bCreate);

        photoAdapter = new PhotoAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        rvPhoto.setLayoutManager(gridLayoutManager);
        rvPhoto.setFocusable(false);
        rvPhoto.setAdapter(photoAdapter);

        bAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });
        bCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //returnMain();
                CallApiCreateStudent();
            }

        });
    }

    private void requestPermission() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openBottomPicker();
                //Toast.makeText(AddImageStudentActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(AddImageStudentActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();
    }

    private void openBottomPicker() {
        TedBottomPicker.with(this)
                .setPeekHeight(1600)
                .showTitle(false)
                .setCompleteButtonText("Done")
                .setEmptySelectionText("No Select")
                .showMultiImage(new TedBottomSheetDialogFragment.OnMultiImageSelectedListener() {
                    @Override
                    public void onImagesSelected(List<Uri> uriList) {
                        // here is selected image uri list
                        imgList = uriList;
                        if (uriList != null && !uriList.isEmpty()) {
                            photoAdapter.setData(uriList);
                        }
                    }
                });
    }

    private void returnMain() {
        Intent intent = new Intent(AddImageStudentActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void CallApiCreateStudent()
    {
        progressBar.setVisibility(View.VISIBLE);

        // lay đường dẫn thực tế đến 3 file hình
        File image1 = new File(RealPathUtil.getRealPath(this,imgList.get(0)));
        File image2 = new File(RealPathUtil.getRealPath(this,imgList.get(1)));
        File image3 = new File(RealPathUtil.getRealPath(this,imgList.get(2)));
//        for (Uri img : imgList) {
//            image = new File(RealPathUtil.getRealPath(this,img));
//        }
        RequestBody requestBodyUsername = RequestBody.create(MediaType.parse("multipart/form-data"),newStudent.username);
        RequestBody requestBodyPassword = RequestBody.create(MediaType.parse("multipart/form-data"),newStudent.password);
        RequestBody requestBodyStudentid = RequestBody.create(MediaType.parse("multipart/form-data"),newStudent.studentId);
        RequestBody requestBodyName = RequestBody.create(MediaType.parse("multipart/form-data"),newStudent.name);
        RequestBody requestBodyEmail = RequestBody.create(MediaType.parse("multipart/form-data"),newStudent.email);
        RequestBody requestBodyBirthDay = RequestBody.create(MediaType.parse("multipart/form-data"),newStudent.birthDay);

        RequestBody requestBodyImage1 = RequestBody.create(MediaType.parse("multipart/form-data"),image1);
        MultipartBody.Part multipartBodyimage1 = MultipartBody.Part.createFormData(Const.KEY_IMAGE1,image1.getName(),requestBodyImage1);

        RequestBody requestBodyImage2 = RequestBody.create(MediaType.parse("multipart/form-data"),image2);
        MultipartBody.Part multipartBodyimage2 = MultipartBody.Part.createFormData(Const.KEY_IMAGE2,image2.getName(),requestBodyImage2);

        RequestBody requestBodyImage3 = RequestBody.create(MediaType.parse("multipart/form-data"),image3);
        MultipartBody.Part multipartBodyimage3 = MultipartBody.Part.createFormData(Const.KEY_IMAGE3,image3.getName(),requestBodyImage3);

        // goi api insert student đến server
        StudentRepository studentRepository = getRetrofit().create(StudentRepository.class);
        Call<InsertResultModel> call = studentRepository.CreateNewStudent(requestBodyUsername,
                requestBodyPassword,
                requestBodyEmail,
                requestBodyBirthDay,
                requestBodyName,
                requestBodyStudentid,
                multipartBodyimage1,
                multipartBodyimage2,
                multipartBodyimage3);
        call.enqueue(new Callback<InsertResultModel>() {
            @Override
            public void onResponse(Call<InsertResultModel> call, Response<InsertResultModel> response) {
                // xu ly message
                Toast.makeText(AddImageStudentActivity.this, response.body().message, Toast.LENGTH_SHORT).show();
                returnMain();
            }

            @Override
            public void onFailure(Call<InsertResultModel> call, Throwable t) {

            }
        });
    }
}