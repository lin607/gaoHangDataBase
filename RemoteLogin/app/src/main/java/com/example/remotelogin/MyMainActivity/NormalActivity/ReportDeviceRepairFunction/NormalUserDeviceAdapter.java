package com.example.remotelogin.MyMainActivity.NormalActivity.ReportDeviceRepairFunction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remotelogin.R;

import java.util.ArrayList;
import java.util.List;

public class NormalUserDeviceAdapter extends RecyclerView.Adapter<NormalUserDeviceAdapter.DeviceHolder> {

    private List<NormalUserDevice> devices = new ArrayList<>();
    private EditText id;

    public NormalUserDeviceAdapter(EditText id) {
        this.id = id;
    }

    public void setDevices(List<NormalUserDevice> devices) {
        this.devices.clear();
        this.devices.addAll(devices);
    }

    @NonNull
    @Override
    public DeviceHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.cell, parent, false);
        return new DeviceHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull final NormalUserDeviceAdapter.DeviceHolder holder, int position) {
        String adevice_id = devices.get(position).getId();
        String adevice_type = devices.get(position).getType();
        holder.device_id.setText(adevice_id);
        holder.device_type.setText(adevice_type);
        holder.Deviceview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                String dev_id = devices.get(position).getId();
                id.setText(dev_id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    static class DeviceHolder extends RecyclerView.ViewHolder {
        TextView device_id;
        TextView device_type;
        View Deviceview;

        public DeviceHolder(@NonNull View itemView) {
            super(itemView);
            device_id = (TextView) itemView.findViewById(R.id.device_id);
            device_type = (TextView) itemView.findViewById(R.id.device_type);
            Deviceview = itemView;
        }
    }
}