package com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanDataBaseOperation;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanForms;
import com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanFragmentPagerAdapter;
import com.example.remotelogin.MyMainActivity.AdminActivity.GetInfo.GetInfoUser;
import com.example.remotelogin.MyMainActivity.AdminActivity.GetInfo.InfoUser;
import com.example.remotelogin.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FindAllCleanInfo extends AsyncTask<Void, Void, List<CleanForms>> {
    final private String TAG = "MYTAG";
    private Context context;

    public interface CallBacks {
        void getAllCleanInfo(List<CleanForms> cleanFormsList);
    }

    private FindAllCleanInfo.CallBacks callBacks;

    public void setCallBacks(FindAllCleanInfo.CallBacks callBacks) {
        this.callBacks = callBacks;
    }

    public FindAllCleanInfo(Context context) {
        this.context = context;
    }

    @Override
    protected List<CleanForms> doInBackground(Void... voids) {
        String url = "jdbc:mysql://" + context.getString(R.string.ip) + ":" + context.getString(R.string.port)
                + "/" + context.getString(R.string.dbName); // 构建连接mysql的字符串
        String user = context.getString(R.string.user_id);
        String password = context.getString(R.string.password);
        List<CleanForms> cleanFormsList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            Log.e(TAG, "远程连接成功!");
        } catch (SQLException e) {
            Log.e(TAG, "远程连接失败!");
        }
        if (conn != null) {
            String sql = "select * from clean_info";
//                String sql_normal = "select * from normal_user where id = " + id;
            try {
                java.sql.Statement statement = (Statement) conn.createStatement();
                ResultSet rSet = statement.executeQuery(sql);
                while (rSet.next()) {
                    Log.e(TAG, rSet.getString("id"));
                    CleanForms cleanForms = new CleanForms();
                    cleanForms.setId(rSet.getInt("id"));
                    cleanForms.setCleanDate(rSet.getDate("clean_date"));
                    cleanForms.setCleanMachineId(rSet.getInt("clean_machine_id"));
                    cleanForms.setCleanUserId(rSet.getInt("clean_user_id"));
                    cleanFormsList.add(cleanForms);
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
        return cleanFormsList;
    }

    @Override
    protected void onPostExecute(List<CleanForms> list) {
        super.onPostExecute(list);
        if (!list.isEmpty()) {
            callBacks.getAllCleanInfo(list);
        } else {
            Toast.makeText(context.getApplicationContext(), "没有保洁记录！", Toast.LENGTH_LONG).show();
        }
    }
}