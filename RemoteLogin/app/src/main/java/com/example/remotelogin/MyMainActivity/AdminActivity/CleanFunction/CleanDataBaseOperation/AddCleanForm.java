package com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanDataBaseOperation;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanForms;
import com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanStaffInfo;
import com.example.remotelogin.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AddCleanForm extends AsyncTask<List<CleanForms>, Void, Void> {
    final private String TAG = "MYTAG";
    private Context context;

    public AddCleanForm(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(List<CleanForms>... cleanFormList) {
        List<CleanForms> cleanForms = cleanFormList[0];
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
            try {
                java.sql.Statement statement = (Statement) conn.createStatement();
                for (int i = 0; i < cleanForms.size(); i++) {
                    String sql = "insert into clean_info " +
                            "(id, clean_date, clean_machine_id, clean_user_id) " +
                            "values (" +
                            cleanForms.get(i).getId() + ", '" +
                            cleanForms.get(i).getCleanDate() + "'," +
                            cleanForms.get(i).getCleanMachineId() + "," +
                            cleanForms.get(i).getCleanUserId() + "')";
                    statement.executeUpdate(sql);
                }
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
        Toast.makeText(context.getApplicationContext(), "添加表单成功", Toast.LENGTH_LONG).show();
    }
}