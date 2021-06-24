package com.example.remotelogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.example.remotelogin.Login.MyLoginActivity;

public class SkipActivity extends AppCompatActivity {
    private TextView skip;
    private int TIME = 3;
    private boolean isSkip = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skip);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide(); //隐藏标题栏
        }
        skip = (TextView) findViewById(R.id.skip);

        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case -2:
                        skip.setText("skip(" + TIME + "s)");
                        break;
                    case 1:
                        if (!isSkip) {
                            Intent intent = new Intent(SkipActivity.this, MyLoginActivity.class);
                            startActivity(intent);
                            isSkip = true;
                            SkipActivity.this.finish();
                        }
                        break;
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; TIME > 0; TIME--) {
                    handler.sendEmptyMessage(-2);
                    if (TIME <= 0)
                        break;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.sendEmptyMessage(1);
            }
        }).start();

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SkipActivity.this, MyLoginActivity.class);
                startActivity(intent);
                isSkip = true;
                SkipActivity.this.finish();
            }
        });
    }
}