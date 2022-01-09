package com.example.lib.Repository;

import com.example.lib.Models.Const;
import com.example.lib.Models.InsertModel.InsertResultModel;
import com.example.lib.Models.InsertModel.StudentInsertModel;
import com.example.lib.Models.StudentModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface StudentRepository {
    @GET("api/Students/get-student-list")
    Call<StudentModel> GetStudentList();
    @Multipart
    @POST("api/Students/create-student")
    Call<InsertResultModel> CreateNewStudent(@Part(Const.KEY_USERNAME)RequestBody username,
                                             @Part(Const.KEY_PASSWORD)RequestBody password,
                                             @Part(Const.KEY_EMAIL) RequestBody email,
                                             @Part(Const.KEY_BIRTHDAY) RequestBody birthday,
                                             @Part(Const.KEY_NAME)RequestBody name,
                                             @Part(Const.KEY_STUDENTID)RequestBody studentid,
                                             @Part MultipartBody.Part image1,
                                             @Part MultipartBody.Part image2,
                                             @Part MultipartBody.Part image3
                                             );
}
