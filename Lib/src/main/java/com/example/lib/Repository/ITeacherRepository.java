package com.example.lib.Repository;

import com.example.lib.Models.AttendanceModel;
import com.example.lib.Models.ClassModel;
import com.example.lib.Models.Const;
import com.example.lib.Models.EnrollmentModel;
import com.example.lib.Models.InsertModel.ClassInsertModel;
import com.example.lib.Models.ResultModels.AddClassResultModel;
import com.example.lib.Models.StudentModel;
import com.example.lib.Models.TeacherModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ITeacherRepository {
    @POST("api/Classes/add-new-class")
    Call<AddClassResultModel> AddNewClass(@Body ClassInsertModel classModel);
    @GET("api/Classes/get-class-list-teacherid")
    Call<List<ClassModel>> GetClassListByTeacherId(@Query("teacherId") String teacherId);
    @GET("api/Enrollments/get-enrollment-by-classId")
    Call<List<EnrollmentModel>> GetEnrollmentListByClassId(@Query("classId")String classId);
    @GET("api/Attendences/get-attendance-by-enrollmentid")
    Call<List<AttendanceModel>> GetAttendanceByEnrollmentId(@Query("enrollmentId")int enrollmentId);

    @POST("api/Teachers/class-attendance")
    @Multipart
    Call<List<StudentModel>> SaveAttendances(@Part MultipartBody.Part classImage,
                                         @Part(Const.KEY_CLASSID)RequestBody classId);

}
