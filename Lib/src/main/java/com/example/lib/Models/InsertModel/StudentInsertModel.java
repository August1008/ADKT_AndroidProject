package com.example.lib.Models.InsertModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class StudentInsertModel implements Parcelable {
    public String username;
    public String password;
    public int userType;
    public String studentId;
    public String name;
    public String birthDay;
    public String email;
    public String userId;
    public String[] images;


    public StudentInsertModel(String username, String password, int userType, String studentId, String name, String birthDay, String email) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.studentId = studentId;
        this.name = name;
        this.birthDay = birthDay;
        this.email = email;
    }

    protected StudentInsertModel(Parcel in) {
        username = in.readString();
        password = in.readString();
        userType = in.readInt();
        studentId = in.readString();
        name = in.readString();
        birthDay = in.readString();
        email = in.readString();
        userId = in.readString();
    }

    public static final Creator<StudentInsertModel> CREATOR = new Creator<StudentInsertModel>() {
        @Override
        public StudentInsertModel createFromParcel(Parcel in) {
            return new StudentInsertModel(in);
        }

        @Override
        public StudentInsertModel[] newArray(int size) {
            return new StudentInsertModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
        dest.writeInt(userType);
        dest.writeString(studentId);
        dest.writeString(name);
        dest.writeString(birthDay);
        dest.writeString(email);
        dest.writeString(userId);
    }
}
