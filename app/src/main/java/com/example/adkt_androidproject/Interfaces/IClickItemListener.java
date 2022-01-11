package com.example.adkt_androidproject.Interfaces;

import com.example.lib.Models.ClassModel;
import com.example.lib.Models.EnrollmentModel;

public interface IClickItemListener {
    void onClickItem(String str);
    void onClickEnrollment(EnrollmentModel model);
}
