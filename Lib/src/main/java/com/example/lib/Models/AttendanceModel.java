package com.example.lib.Models;

public class AttendanceModel {
    public boolean status;
    public String GetStatus()
    {
        return status ? "attendant" : "missing";
    }
}
