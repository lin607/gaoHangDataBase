package com.example.remotelogin.MyMainActivity.NormalActivity.ReportDeviceRepairFunction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.remotelogin.BaseActivity;
import com.example.remotelogin.R;

public class NormalUserDeviceRepairActivity extends BaseActivity {
    Button report;
    Button history;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user_device_repair);
        report = (Button) this.findViewById(R.id.report);
        history = (Button) this.findViewById(R.id.history);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NormalUserDeviceRepairActivity.this, NormalUserReportActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NormalUserDeviceRepairActivity.this, NormalUserDeviceHistoryActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }
}