package com.example.remotelogin.MyMainActivity.NormalActivity.ReportDeviceRepairFunction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.remotelogin.BaseActivity;
import com.example.remotelogin.R;

import java.util.List;

public class NormalUserStaffActivity extends BaseActivity {
    NormalUserStaffAdapter staffAdapter;
    RecyclerView recyclerView;
    NormalUserDeviceViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user_staff);
        recyclerView = (RecyclerView)this.findViewById(R.id.staff_recyc);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        staffAdapter = new NormalUserStaffAdapter(NormalUserStaffActivity.this);
        recyclerView.setAdapter(staffAdapter);

        userViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(NormalUserDeviceViewModel.class);
        userViewModel.Staff_info();

        userViewModel.getAllstaffs().observe(NormalUserStaffActivity.this, new Observer<List<NormalUserStaff>>() {
            @Override
            public void onChanged(List<NormalUserStaff> staff) {
                staffAdapter.setStaffs(staff);
                staffAdapter.notifyDataSetChanged();
            }
        });
    }
}