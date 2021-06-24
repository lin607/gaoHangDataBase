package com.example.remotelogin.MyMainActivity.NormalActivity.ChangePasswordFunction;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.remotelogin.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IDFindPwd extends AsyncTask<String, Void, String> {
    final private String TAG = "MYTAG";
    private Context context;

    public interface CallBacks {
        void getPassword(String password);
    }

    private IDFindPwd.CallBacks callBacks;

    public void setCallBacks(IDFindPwd.CallBacks callBacks) {
        this.callBacks = callBacks;
    }


    IDFindPwd(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        String url = "jdbc:mysql://" + context.getString(R.string.ip) + ":" + context.getString(R.string.port)
                + "/" + context.getString(R.string.dbName); // 构建连接mysql的字符串
        String user = context.getString(R.string.user_id);
        String password = context.getString(R.string.password);
        String findPwd = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            Log.e(TAG, "远程连接成功!");
        } catch (SQLException e) {
            Log.e(TAG, "远程连接失败!");
        }
        if (conn != null) {
            String sql_normal = "select password from normal_user where id = " + strings[0];
            try {
                java.sql.Statement statement = (Statement) conn.createStatement();
                ResultSet rSet = statement.executeQuery(sql_normal);
                while (rSet.next()) {
                    Log.e(TAG, rSet.getString("password"));
                    findPwd = rSet.getString("password");
                }
                statement.close();
                rSet.close();
            } catch (SQLException e) {
                Log.e(TAG, "creatStatement error!");
            }
        }
        try {
            conn.close();
        } catch (SQLException e) {
            Log.e(TAG, "关闭连接失败!");
        }
        return findPwd;
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);
        callBacks.getPassword(string);
    }
}