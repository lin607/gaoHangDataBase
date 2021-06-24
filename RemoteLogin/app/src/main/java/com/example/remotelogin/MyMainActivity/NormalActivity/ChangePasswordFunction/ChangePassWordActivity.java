package com.example.remotelogin.MyMainActivity.NormalActivity.ChangePasswordFunction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.remotelogin.BaseActivity;
import com.example.remotelogin.R;

import static java.lang.Thread.sleep;

public class ChangePassWordActivity extends BaseActivity implements IDFindPwd.CallBacks {
    private String id;
    private String oriPWD;
    private String newPWD;
    private String repeatPWD;
    private String dbString;
    private EditText oriPassword_edit;
    private EditText newPassword_edit;
    private EditText repeatPassword_edit;
    private Button change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass_word);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        oriPassword_edit = findViewById(R.id.originPassword);
        newPassword_edit = findViewById(R.id.newPassword);
        repeatPassword_edit = findViewById(R.id.repeatNewPassword);
        change = findViewById(R.id.changPwd_bt);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oriPWD = oriPassword_edit.getText().toString();
                newPWD = newPassword_edit.getText().toString();
                repeatPWD = repeatPassword_edit.getText().toString();
                IDFindPwd idFindPwd = new IDFindPwd(getApplicationContext());
                idFindPwd.setCallBacks(ChangePassWordActivity.this::getPassword);
                idFindPwd.execute(id);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
//                while (dbString == null);
                if (dbString != null && !dbString.equals(oriPWD)) {
                    Toast.makeText(v.getContext(), "重新输入旧密码", Toast.LENGTH_LONG).show();
                }
                if (newPWD != null && !newPWD.equals(repeatPWD)) {
                    Toast.makeText(v.getContext(), "输入新密码不一致", Toast.LENGTH_LONG).show();
                }
                if (dbString != null && newPWD != null && newPWD.equals(repeatPWD)) {
                    IdChangePwd idChangePwd = new IdChangePwd(v.getContext());
                    idChangePwd.execute(id, newPWD);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        });
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // must store the new intent unless getIntent() will return the old one
        setIntent(intent);
    }

    @Override
    public void getPassword(String password) {
        dbString = password;
    }
}