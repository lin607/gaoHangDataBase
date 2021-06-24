package com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanDataBaseOperation;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.remotelogin.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class IDDeleteCleanInfo extends AsyncTask<String, Void, Void> {
    final private String TAG = "MYTAG";
    private Context context;

    public IDDeleteCleanInfo(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(String... strings) {
        String url = "jdbc:mysql://" + context.getString(R.string.ip) + ":" + context.getString(R.string.port)
                + "/" + context.getString(R.string.dbName); // 构建连接mysql的字符串
        String user = context.getString(R.string.user_id);
        String password = context.getString(R.string.password);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            Log.e(TAG, "远程连接成功!");
        } catch (SQLException e) {
            Log.e(TAG, "远程连接失败!");
        }
        if (conn != null) {
            String sql = "delete from clean_info where id=" + strings[0];
            try {
                java.sql.Statement statement = (Statement) conn.createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException e) {
//                Toast.makeText(context.getApplicationContext(), "修改密码失败", Toast.LENGTH_LONG).show();
                Log.e(TAG, "creatStatement error!");
            }
        }
        try {
            conn.close();
        } catch (SQLException e) {
            Log.e(TAG, "关闭连接失败!");
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        Toast.makeText(context.getApplicationContext(), "删除保洁记录成功", Toast.LENGTH_LONG).show();
    }
}