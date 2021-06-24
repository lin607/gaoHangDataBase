package com.example.remotelogin.Login;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.remotelogin.BaseActivity;
import com.example.remotelogin.MyMainActivity.AdminActivity.AdminMainActivity;
import com.example.remotelogin.MyMainActivity.AdminActivity.BoardFunction.AdminBoardViewModel;
import com.example.remotelogin.MyMainActivity.NormalActivity.NormalUserMainActivity;
import com.example.remotelogin.R;

import java.io.Serializable;

import static java.lang.Thread.sleep;

public class MyLoginActivity extends BaseActivity {
    EditText pw_edit_text;
    EditText user_edit_text;
    Button login_btn;
    AdminUser adminUser;
    NormalUser normalUser;
    String user;
    String pw;
    FindUserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide(); //隐藏标题栏
        }
        pw_edit_text = findViewById(R.id.pw_edit_text);
        user_edit_text = findViewById(R.id.userid_edit_text);
        login_btn = findViewById(R.id.login_btn);
        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).
                get(FindUserViewModel.class);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户输入
                user = user_edit_text.getText().toString();
                pw = pw_edit_text.getText().toString();
                if (user.isEmpty() || pw.isEmpty()) {
                    Toast.makeText(v.getContext(), "请输入正确的账号和密码！", Toast.LENGTH_LONG).show();
                    return;
                }

                viewModel.loadUser(user);
                // todo 寻找normalUser


                if (normalUser != null && pw.equals(normalUser.getPassword())) {
                    // todo 传递User变量
                    Intent intent = new Intent(v.getContext(), NormalUserMainActivity.class);
                    intent.putExtra("normal_user", (Serializable) normalUser);
                    v.getContext().startActivity(intent);
                } else if (adminUser != null && pw.equals(adminUser.getPassword())) {
                    Intent intent = new Intent(v.getContext(), AdminMainActivity.class);
                    intent.putExtra("admin_user", (Serializable) adminUser);
                    v.getContext().startActivity(intent);
                }
            }
        });

        viewModel.viewModelGetAdminUser().observe(this, new Observer<AdminUser>() {
            @Override
            public void onChanged(AdminUser adminUser) {
                if (adminUser != null) {
                    if (pw.equals(adminUser.getPassword())) {
                        Intent intent = new Intent(MyLoginActivity.this, AdminMainActivity.class);
                        intent.putExtra("admin_user", (Serializable) adminUser);
                        startActivity(intent);
                    }
                }
            }
        });
        viewModel.viewModelGetNormalUser().observe(this, new Observer<NormalUser>() {
            @Override
            public void onChanged(NormalUser normalUser) {
                if (normalUser != null) {
                    Intent intent = new Intent(MyLoginActivity.this, NormalUserMainActivity.class);
                    intent.putExtra("normal_user", (Serializable) normalUser);
                    startActivity(intent);
                }
            }
        });
    }
}