package com.example.remotelogin.MyMainActivity.NormalActivity.BoardCommentFunction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.remotelogin.BaseActivity;
import com.example.remotelogin.R;

import java.util.List;

public class NormalUserContentActivity extends BaseActivity {
    TextView title;
    TextView content;
    TextView time;
    EditText comm_text;
    Button comment;
    String name;
    String board_id;
    int id;
    NormalUserBoardViewModel viewModel;
    NormalUserCommentAdapter commentAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user_content);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        title = (TextView) this.findViewById(R.id.Con_title);
        content = (TextView) this.findViewById(R.id.content);
        time = (TextView) this.findViewById(R.id.time);
        comm_text = (EditText) this.findViewById(R.id.comm_text);
        comment = (Button) this.findViewById(R.id.comm_but);

        Intent intent = getIntent();
        final String atitle = intent.getStringExtra("Title");
        title.setText(atitle);
        name = intent.getStringExtra("name");
        id = intent.getIntExtra("id",-1);
        board_id = intent.getStringExtra("board_id");

        recyclerView = (RecyclerView) this.findViewById(R.id.recyc_comm);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        commentAdapter = new NormalUserCommentAdapter();
        recyclerView.setAdapter(commentAdapter);

        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(NormalUserBoardViewModel.class);
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
                s = s.substring(0, 19);
                time.setText(s);
            }
        });
        viewModel.getComments().observe(this, new Observer<List<NormalUserComment>>() {
            @Override
            public void onChanged(List<NormalUserComment> comments) {
                commentAdapter.setComments(comments);
                commentAdapter.notifyDataSetChanged();
            }
        });

        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comm = comm_text.getText().toString();
                if (!comm.isEmpty()) {
                    NormalUserComment comment = new NormalUserComment(name, "", comm);
                    comm_text.setText("");
                    comment.setId(String.valueOf(id));
                    viewModel.Board_addcomment(comment);
                    viewModel.Board_comment();
                }
            }
        });
    }
}