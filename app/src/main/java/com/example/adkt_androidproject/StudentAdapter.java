package com.example.adkt_androidproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adkt_androidproject.Interfaces.IClickItemListener;
import com.example.lib.Models.EnrollmentModel;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.SubjectViewHolder>{

    private List<EnrollmentModel> list;
    private IClickItemListener iClickItemListener;

    public StudentAdapter(List<EnrollmentModel> list, IClickItemListener listener) {
        this.list = list;
        this.iClickItemListener = listener;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentAdapter.SubjectViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        EnrollmentModel enrollmentModel = list.get(position);
        if (enrollmentModel == null) {
            return;
        }
        holder.tvStudentName.setText(enrollmentModel.studentName);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemListener.onClickEnrollment(enrollmentModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder {

        TextView tvStudentName;
        LinearLayout layout;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.item_student);
            tvStudentName = itemView.findViewById(R.id.tvStudentName);
        }
    }
}
