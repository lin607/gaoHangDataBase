package com.example.remotelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    String TAG = "MYTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String ip = "172.24.162.6";
                int port = 3306;
                String dbName = "ms";
                String url = "jdbc:mysql://" + ip + ":" + port
                        + "/" + dbName; // 构建连接mysql的字符串
                String user = "jason";
                String password ="123456";
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection(url, user, password);
                    Log.e(TAG,"远程连接成功!");
                }catch (SQLException e){
                    Log.e(TAG,"远程连接失败!");
                }

                if(conn != null){
                    String sql = "select * from admin_user";

                    try {
                        java.sql.Statement statement = (Statement)conn.createStatement();
                        ResultSet rSet = statement.executeQuery(sql);
                        while(rSet.next()){
                            Log.e(TAG,rSet.getString("id"));
                        }
                        statement.close();
                        rSet.close();
                    }catch (SQLException e){
                        Log.e(TAG,"creatStatement error!");
                    }
                }

                try {
                    conn.close();
                }catch (SQLException e){
                    Log.e(TAG,"关闭连接失败!");
                }
            }
        });
        thread.start();
    }
}