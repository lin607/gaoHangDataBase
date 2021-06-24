package com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanDataBaseOperation;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanStaffInfo;
import com.example.remotelogin.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FindAllStaff extends AsyncTask<Void, Void, List<CleanStaffInfo>> {
    final private String TAG = "MYTAG";
    private Context context;

    public interface CallBacks {
        void getAllCleanStaff(List<CleanStaffInfo> adminUserList);
    }

    private FindAllStaff.CallBacks callBacks;

    public void setCallBacks(FindAllStaff.CallBacks callBacks) {
        this.callBacks = callBacks;
    }

    public FindAllStaff(Context context) {
        this.context = context;
    }

    @Override
    protected List<CleanStaffInfo> doInBackground(Void... voids) {
        String url = "jdbc:mysql://" + context.getString(R.string.ip) + ":" + context.getString(R.string.port)
                + "/" + context.getString(R.string.dbName); // 构建连接mysql的字符串
        String user = context.getString(R.string.user_id);
        String password = context.getString(R.string.password);
        List<CleanStaffInfo> cleanStaffInfoList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            Log.e(TAG, "远程连接成功!");
        } catch (SQLException e) {
            Log.e(TAG, "远程连接失败!");
        }
        if (conn != null) {
            String sql = "select * from info_clean_staff";
//                String sql_normal = "select * from normal_user where id = " + id;
            try {
                java.sql.Statement statement = (Statement) conn.createStatement();
                ResultSet rSet = statement.executeQuery(sql);
                while (rSet.next()) {
                    Log.e(TAG, rSet.getString("id"));
                    CleanStaffInfo cleanStaffInfo = new CleanStaffInfo();
                    cleanStaffInfo.setId(rSet.getInt("id"));
                    cleanStaffInfo.setName(rSet.getString("name"));
                    cleanStaffInfo.setHireDate(rSet.getDate("hire_date"));
                    cleanStaffInfo.setPhoneNumber(rSet.getString("phone_number"));
                    cleanStaffInfo.setSex(rSet.getString("sex"));
                    cleanStaffInfoList.add(cleanStaffInfo);
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
        return cleanStaffInfoList;
    }

    @Override
    protected void onPostExecute(List<CleanStaffInfo> cleanStaffInfoList) {
        super.onPostExecute(cleanStaffInfoList);
        if (!cleanStaffInfoList.isEmpty()) {
            callBacks.getAllCleanStaff(cleanStaffInfoList);
        } else {
            Toast.makeText(context.getApplicationContext(), "没有保洁人员记录！", Toast.LENGTH_LONG).show();
        }
    }
}