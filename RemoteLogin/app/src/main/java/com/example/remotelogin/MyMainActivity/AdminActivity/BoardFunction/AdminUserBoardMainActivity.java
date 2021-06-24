package com.example.remotelogin.MyMainActivity.AdminActivity.BoardFunction;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.remotelogin.BaseActivity;
import com.example.remotelogin.R;

import java.util.List;

public class AdminUserBoardMainActivity extends BaseActivity {
    RecyclerView recyclerView;
    AdminUserBoardAdapter boardAdapter;
    AdminBoardViewModel viewModel;
    Button new_but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_board_main);
        new_but = (Button) this.findViewById(R.id.new_but);
        recyclerView = (RecyclerView) this.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        boardAdapter = new AdminUserBoardAdapter(AdminUserBoardMainActivity.this);
        recyclerView.setAdapter(boardAdapter);

        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(AdminBoardViewModel.class);
        viewModel.Board_titles();
        viewModel.getTitles().observe((LifecycleOwner) this, new Observer<List<AdminUserTitle>>() {
            @Override
            public void onChanged(List<AdminUserTitle> adminUserTitles) {
                boardAdapter.setTitles(adminUserTitles);
                boardAdapter.notifyDataSetChanged();
            }
        });

        new_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminUserBoardMainActivity.this, AdminUserNewBoardActivity.class);
                startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, "Delete Successfully", Toast.LENGTH_SHORT).show();
                }
            case 2:
                viewModel.Board_titles();
                break;
            default:
        }
    }
}