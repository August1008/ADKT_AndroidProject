package com.example.adkt_androidproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adkt_androidproject.Interfaces.IClickItemListener;
import com.example.lib.Models.ClassModel;

import java.util.List;

public class ClassForTeacherAdapter extends RecyclerView.Adapter<ClassForTeacherAdapter.SubjectForTeacherViewHolder> {

    private List<ClassModel> list;
    private IClickItemListener iClickItemListener;

    public ClassForTeacherAdapter(List<ClassModel> list, IClickItemListener listener) {
        this.list = list;
        this.iClickItemListener = listener;
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
         ClassModel classModel = list.get(position);
         if (classModel == null) {
             return;
         }
         holder.tvSubjectName.setText(classModel.subject);
         holder.tvclassId.setText(classModel.classId);
         holder.layout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 iClickItemListener.onClickItem(classModel.classId);
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

    public class SubjectForTeacherViewHolder extends RecyclerView.ViewHolder {

        TextView tvSubjectName,tvclassId;
        LinearLayout layout;

        public SubjectForTeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.item_subjects);
            tvSubjectName = itemView.findViewById(R.id.tvSubjectName);
            tvclassId = itemView.findViewById(R.id.tvclassId);
        }
    }
}
