package com.example.adkt_androidproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SubjectForTeacherAdapter extends RecyclerView.Adapter<SubjectForTeacherAdapter.SubjectForTeacherViewHolder> {

    private List<String> list;

    public SubjectForTeacherAdapter(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubjectForTeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subjects, parent, false);
        return new SubjectForTeacherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectForTeacherViewHolder holder, int position) {
         String str = list.get(position);
         if (str == null) {
             return;
         }
         holder.tvSubjectName.setText("Subject: " + str);
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    public class SubjectForTeacherViewHolder extends RecyclerView.ViewHolder {

        TextView tvSubjectName;

        public SubjectForTeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubjectName = itemView.findViewById(R.id.tvSubjectName);
        }
    }
}
