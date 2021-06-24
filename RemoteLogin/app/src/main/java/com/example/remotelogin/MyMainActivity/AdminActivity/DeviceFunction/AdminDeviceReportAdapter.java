package com.example.remotelogin.MyMainActivity.AdminActivity.DeviceFunction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remotelogin.R;

import java.util.ArrayList;
import java.util.List;

public class AdminDeviceReportAdapter extends RecyclerView.Adapter<AdminDeviceReportAdapter.ReportHolder> {

    private List<AdminDeviceReport> reports = new ArrayList<>();
    private EditText id;
    Context context;

    public AdminDeviceReportAdapter(EditText id, Context context) {
        this.id = id;
        this.context = context;
    }

    public void setReports(List<AdminDeviceReport> reports) {
        this.reports.clear();
        this.reports.addAll(reports);
    }

    @NonNull
    @Override
    public AdminDeviceReportAdapter.ReportHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.report_cell, parent, false);
        return new AdminDeviceReportAdapter.ReportHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdminDeviceReportAdapter.ReportHolder holder, int position) {
        holder.Report_id.setText(reports.get(position).getReport_id());
        holder.date.setText(reports.get(position).getDate());
        holder.device_id.setText(reports.get(position).getDevice().getId());
        holder.device_type.setText(reports.get(position).getDevice().getType());
        holder.staff_name.setText(reports.get(position).getStaff_name());
        holder.staff_phone.setText(reports.get(position).getStaff_phone());
        holder.user_name.setText(reports.get(position).getUser_name());
        holder.user_phone.setText(reports.get(position).getUser_phone());
        String f = reports.get(position).getFinish();
        String m = reports.get(position).getScore();
        String no = "Not Finished";
        String mar = "No score";
        if (f.equals("0")) {
            holder.finish.setText(no);
        } else if (m == null) {
            holder.finish.setText(mar);
        } else {
            holder.finish.setText(reports.get(position).getScore());
        }
        holder.Reportview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (reports.get(position).getFinish().equals("1")) {
                    Toast.makeText(context, "Finished!", Toast.LENGTH_SHORT).show();
                } else {
                    id.setText(reports.get(position).getReport_id());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    static class ReportHolder extends RecyclerView.ViewHolder {
        TextView Report_id;
        TextView date;
        TextView device_id;
        TextView device_type;
        TextView staff_name;
        TextView staff_phone;
        TextView finish;
        TextView user_name;
        TextView user_phone;
        View Reportview;

        public ReportHolder(@NonNull View itemView) {
            super(itemView);
            Report_id = (TextView) itemView.findViewById(R.id.report_id);
            date = (TextView) itemView.findViewById(R.id.report_date);
            device_id = (TextView) itemView.findViewById(R.id.d_id);
            device_type = (TextView) itemView.findViewById(R.id.d_type);
            staff_name = (TextView) itemView.findViewById(R.id.rep_s_name);
            staff_phone = (TextView) itemView.findViewById(R.id.staff_p);
            finish = (TextView) itemView.findViewById(R.id.fini);
            user_name = (TextView) itemView.findViewById(R.id.rep_u_name);
            user_phone = (TextView) itemView.findViewById(R.id.user_p);
            Reportview = itemView;
        }
    }
}
