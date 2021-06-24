package com.example.remotelogin.MyMainActivity.NormalActivity.ReportDeviceRepairFunction;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.remotelogin.BaseActivity;
import com.example.remotelogin.R;

import java.util.List;

public class NormalUserReportActivity extends BaseActivity {
    EditText dev_id;
    EditText staff_name;
    Button report;
    int id;
    String staff_id;
    NormalUserDeviceViewModel userViewModel;
    NormalUserDeviceAdapter deviceAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user_report);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        report = (Button) this.findViewById(R.id.button);
        dev_id = (EditText) this.findViewById(R.id.editText);
        dev_id.setInputType(InputType.TYPE_NULL);
        staff_name = (EditText) this.findViewById(R.id.staff_id);
        staff_name.setInputType(InputType.TYPE_NULL);

        recyclerView = (RecyclerView) this.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        deviceAdapter = new NormalUserDeviceAdapter(dev_id);
        recyclerView.setAdapter(deviceAdapter);

        userViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(NormalUserDeviceViewModel.class);
        userViewModel.Device_info();

        userViewModel.getAlldevices().observe(NormalUserReportActivity.this, new Observer<List<NormalUserDevice>>() {
            @Override
            public void onChanged(List<NormalUserDevice> devices) {
                deviceAdapter.setDevices(devices);
                deviceAdapter.notifyDataSetChanged();
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String device_id = dev_id.getText().toString();
                String name = staff_name.getText().toString();
                if (!device_id.isEmpty()) {
                    if (!name.isEmpty()) {
                        userViewModel.Reportrepair(device_id, String.valueOf(id), staff_id);
                        userViewModel.Device_info();
                    } else {
                        Toast.makeText(v.getContext(), "The staff's name cannot be null!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(v.getContext(), "The id cannot be null!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        staff_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NormalUserStaffActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // TODO 没有super报错
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    if (data != null){
                        staff_name.setText(data.getStringExtra("staff_name"));
                        staff_id = data.getStringExtra("staff_id");
                    }
                }
                break;
            default:
        }
    }
}