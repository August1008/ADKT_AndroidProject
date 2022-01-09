package com.example.lib.Repository;

import retrofit2.Call;

import com.example.lib.Models.LoginUserModel;
import com.example.lib.Models.StudentModel;
import com.example.lib.Models.UserModel;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IUserRepository {
    @POST("api/Users/login")
    Call<StudentModel> Login(@Body LoginUserModel userModel);
}
