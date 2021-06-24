package com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanDataBaseOperation;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanStaffInfo;
import com.example.remotelogin.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AddCleanStaff extends AsyncTask<List<CleanStaffInfo>, Void, Void> {
    final private String TAG = "MYTAG";
    private Context context;

    public AddCleanStaff(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(List<CleanStaffInfo>... cleans) {
        List<CleanStaffInfo> cleanStaffInfos = cleans[0];
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
                for (int i = 0; i < cleanStaffInfos.size(); i++) {
                    String sql = "insert into info_clean_staff " +
                            "(id, name, hire_date, phone_number, sex) " +
                            "values (" +
                            cleanStaffInfos.get(i).getId() + ", '" +
                            cleanStaffInfos.get(i).getName() + "','" +
                            cleanStaffInfos.get(i).getHireDate() + "','" +
                            cleanStaffInfos.get(i).getPhoneNumber() + "','" +
                            cleanStaffInfos.get(i).getSex() + "')";
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
        Toast.makeText(context.getApplicationContext(), "添加保洁人员成功", Toast.LENGTH_LONG).show();
    }
}