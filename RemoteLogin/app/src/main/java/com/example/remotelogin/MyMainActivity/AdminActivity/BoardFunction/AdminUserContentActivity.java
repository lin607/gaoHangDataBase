package com.example.remotelogin.MyMainActivity.AdminActivity.BoardFunction;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.remotelogin.BaseActivity;
import com.example.remotelogin.R;

import java.util.List;

public class AdminUserContentActivity extends BaseActivity {
    TextView title;
    TextView content;
    TextView time;
    Button delete;
    String board_id;
    AdminBoardViewModel viewModel;
    AdminUserCommentAdapter commentAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_content);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        title = (TextView)this.findViewById(R.id.Con_title);
        content = (TextView)this.findViewById(R.id.content);
        time = (TextView)this.findViewById(R.id.time);
        delete = (Button)this.findViewById(R.id.delete);

        final Intent intent = getIntent();
        final String atitle = intent.getStringExtra("Title");
        board_id = intent.getStringExtra("board_id");
        title.setText(atitle);

        recyclerView = (RecyclerView)this.findViewById(R.id.recyc_comm);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        commentAdapter = new AdminUserCommentAdapter();
        recyclerView.setAdapter(commentAdapter);

        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(AdminBoardViewModel.class);
        viewModel.Board_content(board_id);
        viewModel.Board_comment();

        viewModel.getContent().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                content.setText(s);
            }
        });
        viewModel.getContent_time().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                s = s.substring(0,19);
                time.setText(s);
            }
        });
        viewModel.getComments().observe(this, new Observer<List<AdminUserComment>>() {
            @Override
            public void onChanged(List<AdminUserComment> comments) {
                commentAdapter.setComments(comments);
                commentAdapter.notifyDataSetChanged();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminUserContentActivity.this);
                builder.setTitle("Warning");
                builder.setMessage("Are you sure you want to delete this board?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewModel.Board_Delete();
                        Intent intent1 = new Intent();
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}