package com.example.remotelogin.MyMainActivity.NormalActivity.ReportDeviceRepairFunction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remotelogin.R;

import java.util.ArrayList;
import java.util.List;

public class NormalUserStaffAdapter extends RecyclerView.Adapter<NormalUserStaffAdapter.StaffHolder> {

    private List<NormalUserStaff> staffs = new ArrayList<>();
    Context context;

    public NormalUserStaffAdapter(Context context) {
        this.context = context;
    }

    public void setStaffs(List<NormalUserStaff> staffs) {
        this.staffs.clear();
        this.staffs.addAll(staffs);
    }

    @NonNull
    @Override
    public NormalUserStaffAdapter.StaffHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.staff_cell, parent, false);
        return new NormalUserStaffAdapter.StaffHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull final NormalUserStaffAdapter.StaffHolder holder, int position) {
        String astaff_id = staffs.get(position).getId();
        String astaff_name = staffs.get(position).getName();
        String astaff_score = staffs.get(position).getScore();
        String astaff_work = staffs.get(position).getWork();
        holder.staff_id.setText(astaff_id);
        holder.staff_name.setText(astaff_name);
        holder.staff_score.setText(astaff_score);
        holder.staff_work.setText(astaff_work);
        holder.staffview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                String name = staffs.get(position).getName();
                String staff_id = staffs.get(position).getId();
                Intent intent = new Intent();
                intent.putExtra("staff_name", name);
                intent.putExtra("staff_id",staff_id);
                ((NormalUserStaffActivity) context).setResult(Activity.RESULT_OK, intent);
                ((NormalUserStaffActivity) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return staffs.size();
    }

    static class StaffHolder extends RecyclerView.ViewHolder {
        TextView staff_id;
        TextView staff_name;
        TextView staff_score;
        TextView staff_work;
        View staffview;

        public StaffHolder(@NonNull View itemView) {
            super(itemView);
            staff_id = (TextView) itemView.findViewById(R.id.staff_id);
            staff_name = (TextView) itemView.findViewById(R.id.staff_name);
            staff_score = (TextView) itemView.findViewById(R.id.staff_score);
            staff_work = (TextView) itemView.findViewById(R.id.staff_work);
            staffview = itemView;
        }
    }
}
