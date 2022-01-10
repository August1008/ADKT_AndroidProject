package com.example.adkt_androidproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adkt_androidproject.Interfaces.IClickItemListener;

import java.util.List;

public class ClassForTeacherAdapter extends RecyclerView.Adapter<ClassForTeacherAdapter.SubjectForTeacherViewHolder> {

    private List<String> list;
    private IClickItemListener iClickItemListener;

    public ClassForTeacherAdapter(List<String> list, IClickItemListener listener) {
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
         String str = list.get(position);
         if (str == null) {
             return;
         }
         holder.tvSubjectName.setText("Lớp: " + str);

         holder.layout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 iClickItemListener.onClickItem(str);
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

        TextView tvSubjectName;
        LinearLayout layout;

        public SubjectForTeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.item_subjects);
            tvSubjectName = itemView.findViewById(R.id.tvSubjectName);
        }
    }
}
