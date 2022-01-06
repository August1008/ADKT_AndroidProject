package com.example.lib.Repository;

import com.example.lib.Models.InsertModel.InsertResultModel;
import com.example.lib.Models.InsertModel.StudentInsertModel;
import com.example.lib.Models.StudentModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface StudentRepository {
    @GET("api/Students/get-student-list")
    Call<StudentModel> GetStudentList();
    @POST("api/Students/create-student")
    Call<InsertResultModel> CreateNewStudent(@Body StudentInsertModel studentInsertModel);
}
