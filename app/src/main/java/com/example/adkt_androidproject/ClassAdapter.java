package com.example.adkt_androidproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lib.Models.EnrollmentModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder>{
    private List<EnrollmentModel> list;

    public ClassAdapter(List<EnrollmentModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class, parent, false);
        return new ClassAdapter.ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        EnrollmentModel enrollmentModel = list.get(position);
        if (enrollmentModel == null) {
            return;
        }
        holder.tvTimes.setText(enrollmentModel.startDate + " - " + enrollmentModel.endDate);
        holder.tvTeacherName.setText(enrollmentModel.teacherName);
        holder.tvClassname.setText(enrollmentModel.subject);
        holder.layout.setCardBackgroundColor(holder.itemView.getResources().getColor(randomColor(), null));
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder {

        TextView tvClassname, tvTeacherName, tvTimes;
        CardView layout;
        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.item_class);
            tvClassname = itemView.findViewById(R.id.tvClassname);
            tvTeacherName = itemView.findViewById(R.id.tvTeacherName);
            tvTimes = itemView.findViewById(R.id.tvTimes);
        }

    }

    private int randomColor() {
        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.red);
        colorCode.add(R.color.pink);
        colorCode.add(R.color.purple);
        colorCode.add(R.color.deep_purple);
        colorCode.add(R.color.indigo);
        colorCode.add(R.color.blue);
        colorCode.add(R.color.light_blue);
        colorCode.add(R.color.cyan);
        colorCode.add(R.color.teal);
        colorCode.add(R.color.green);
        colorCode.add(R.color.light_green);
        colorCode.add(R.color.lime);
        colorCode.add(R.color.yellow);
        colorCode.add(R.color.amber);
        colorCode.add(R.color.orange);
        colorCode.add(R.color.deep_orange);
        colorCode.add(R.color.brown);
        colorCode.add(R.color.gray);
        colorCode.add(R.color.blue_gray);

        Random random = new Random();
        int number = random.nextInt(colorCode.size());
        return colorCode.get(number);
    }
}
