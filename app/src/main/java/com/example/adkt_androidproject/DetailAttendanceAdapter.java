package com.example.adkt_androidproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DetailAttendanceAdapter extends RecyclerView.Adapter<DetailAttendanceAdapter.DefaultAttendanceViewHolder>{

    private List<String> list;

    public DetailAttendanceAdapter(List<String> list) {
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
        String str = list.get(position);
        if (str == null) {
            return;
        }
        //holder.tvName.setText(str);
        holder.tvSta.setText(str);
        if (str.equals("False")) {
            holder.ivIconStatus.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.outline_highlight_off_black_48dp, null));
            holder.layout.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.red, null));
        } else {
            holder.ivIconStatus.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.baseline_check_circle_outline_black_48dp, null));
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

    public class DefaultAttendanceViewHolder extends RecyclerView.ViewHolder {

        ImageView ivIconStatus;
        TextView tvName, tvSta;
        CardView layout;
        public DefaultAttendanceViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.item_DetailAttendance);
            tvName = itemView.findViewById(R.id.tvName);
            tvSta = itemView.findViewById(R.id.tvSta);
            ivIconStatus = itemView.findViewById(R.id.ivIconStatus);
        }
    }
}
