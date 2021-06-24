package com.example.remotelogin.MyMainActivity.AdminActivity.DeviceFunction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.remotelogin.BaseActivity;
import com.example.remotelogin.R;

import java.util.List;

public class AdminUserDeviceActivity extends BaseActivity {
    EditText mark;
    Button finish_b;
    AdminDeviceReportAdapter reportAdapter;
    RecyclerView recyclerView;
    AdminDeviceViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_device);
        mark = (EditText) this.findViewById(R.id.editText2);
        mark.setInputType(InputType.TYPE_NULL);
        finish_b = (Button) this.findViewById(R.id.finish_b);

        recyclerView = (RecyclerView) this.findViewById(R.id.history_recyc);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reportAdapter = new AdminDeviceReportAdapter(mark, AdminUserDeviceActivity.this);
        recyclerView.setAdapter(reportAdapter);

        userViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(AdminDeviceViewModel.class);
        userViewModel.setContext(this);
        userViewModel.Report_info();

        userViewModel.getAllreports().observe(AdminUserDeviceActivity.this, new Observer<List<AdminDeviceReport>>() {
            @Override
            public void onChanged(List<AdminDeviceReport> reports) {
                reportAdapter.setReports(reports);
                reportAdapter.notifyDataSetChanged();
            }
        });

        finish_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String r_id = mark.getText().toString();
                if (!r_id.isEmpty()) {
                    userViewModel.Finish_repair(r_id);
                    userViewModel.Report_info();
                    mark.setText("");
                    Toast.makeText(AdminUserDeviceActivity.this, "Finished successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AdminUserDeviceActivity.this, "The report id cannot be null!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}