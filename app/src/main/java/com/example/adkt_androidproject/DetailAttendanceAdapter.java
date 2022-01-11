package com.example.adkt_androidproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lib.Models.StudentModel;

import java.util.List;

public class DetailAttendanceAdapter extends RecyclerView.Adapter<DetailAttendanceAdapter.DefaultAttendanceViewHolder>{

    private List<StudentModel> list;

    public DetailAttendanceAdapter(List<StudentModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public DefaultAttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attendance, parent, false);
        return new DetailAttendanceAdapter.DefaultAttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DefaultAttendanceViewHolder holder, int position) {
        StudentModel studentModel = list.get(position);
        if (studentModel == null) {
            return;
        }
        holder.tvName.setText(studentModel.name);
        holder.tvstudentId.setText(studentModel.studentId);
        holder.ivIconStatus.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.baseline_check_circle_outline_black_48dp, null));
        holder.layout.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.green, null));
        }


    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    public class DefaultAttendanceViewHolder extends RecyclerView.ViewHolder {

        ImageView ivIconStatus;
        TextView tvName, tvstudentId;
        CardView layout;
        public DefaultAttendanceViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.item_DetailAttendance);
            tvName = itemView.findViewById(R.id.tvName);
            tvstudentId = itemView.findViewById(R.id.tvstudentId);
            ivIconStatus = itemView.findViewById(R.id.ivIconStatus);
        }
    }
}
