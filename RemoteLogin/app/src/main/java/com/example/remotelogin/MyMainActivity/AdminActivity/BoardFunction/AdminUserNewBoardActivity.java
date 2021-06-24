package com.example.remotelogin.MyMainActivity.AdminActivity.BoardFunction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.remotelogin.BaseActivity;
import com.example.remotelogin.R;

public class AdminUserNewBoardActivity extends BaseActivity {
    Button certain;
    EditText title;
    EditText board_text;
    AdminBoardViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_new_board);
        certain = (Button) this.findViewById(R.id.certain);
        title = (EditText) this.findViewById(R.id.titletext);
        board_text = (EditText) this.findViewById(R.id.boardtext);

        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(AdminBoardViewModel.class);

        certain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title_text = title.getText().toString();
                String content_text = board_text.getText().toString();
                if (!title_text.isEmpty()) {
                    if (!content_text.isEmpty()) {
                        AdminUserBoard board = new AdminUserBoard(title_text, content_text);
                        viewModel.Board_Add(board);
                        finish();
                    } else {
                        Toast.makeText(v.getContext(), "Content cannot be empty!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(v.getContext(), "Title cannot be empty!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}