package com.example.remotelogin.MyMainActivity.NormalActivity.BoardCommentFunction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.remotelogin.BaseActivity;
import com.example.remotelogin.R;

import java.util.List;

public class NormalUserBoardMainActivity extends BaseActivity {
    RecyclerView recyclerView;
    NormalUserBoardAdapter boardAdapter;
    NormalUserBoardViewModel viewModel;
    String name;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user_board_main);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);
        name = intent.getStringExtra("name");
        recyclerView = (RecyclerView)this.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        boardAdapter = new NormalUserBoardAdapter(NormalUserBoardMainActivity.this, name, id);
        recyclerView.setAdapter(boardAdapter);

        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(NormalUserBoardViewModel.class);
        viewModel.Board_titles();
        viewModel.getTitles().observe((LifecycleOwner) this, new Observer<List<NormalUserTitles>>() {
            @Override
            public void onChanged(List<NormalUserTitles> normalUserTitless) {
                boardAdapter.setTitles(normalUserTitless);
                boardAdapter.notifyDataSetChanged();
            }
        });
    }
}