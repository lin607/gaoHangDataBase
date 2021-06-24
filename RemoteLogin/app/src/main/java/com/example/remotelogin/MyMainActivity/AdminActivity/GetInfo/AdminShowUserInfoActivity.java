package com.example.remotelogin.MyMainActivity.AdminActivity.GetInfo;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.remotelogin.BaseActivity;
import com.example.remotelogin.R;

import java.util.ArrayList;
import java.util.List;

public class AdminShowUserInfoActivity extends BaseActivity {
    private InfoAdapter infoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_show_user_info);
        RecyclerView recyclerView = findViewById(R.id.admin_info_user_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        InfoUserViewModel infoUserViewModel = new ViewModelProvider(this).get(InfoUserViewModel.class);
        infoUserViewModel.setContext(this);
        infoUserViewModel.FindAllInfoUsers();
        // todo 使用ViewModel和LiveData改进
        infoUserViewModel.getInfoUsersLiveData().observe(this, new Observer<List<InfoUser>>() {
            @Override
            public void onChanged(List<InfoUser> infoUsers) {
                infoAdapter = new InfoAdapter(infoUsers);
                recyclerView.setAdapter(infoAdapter);
            }
        });
    }

}