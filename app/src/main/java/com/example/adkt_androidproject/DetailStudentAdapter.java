package com.example.adkt_androidproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DetailStudentAdapter extends RecyclerView.Adapter<DetailStudentAdapter.DetailStudentViewHolder>{

    private List<String> list;

    public DetailStudentAdapter(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailStudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail, parent, false);
        return new DetailStudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailStudentViewHolder holder, int position) {
        String str = list.get(position);
        if (str == null) {
            return;
        }
        //holder.tvLesson.setText(str);
        holder.tvTime.setText("09/05/2000 - 12:30");
        holder.tvStatus.setText(str);
        if (str.equals("False")) {
            holder.layout.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.red, null));
        } else {
            holder.layout.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.green, null));
        }
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    public class DetailStudentViewHolder extends RecyclerView.ViewHolder {

        TextView tvLesson, tvStatus, tvTime;
        CardView layout;
        public DetailStudentViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.item_attendance);
            tvLesson = itemView.findViewById(R.id.tvLesson);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
}
