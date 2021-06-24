package com.example.remotelogin.MyMainActivity.AdminActivity.GetInfo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.remotelogin.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GetInfoUser extends AsyncTask<Void, Void, List<InfoUser>> {
    final private String TAG = "MYTAG";
    private Context context;

    public interface CallBacks {
        void getInfoUsers(List<InfoUser> adminUserList);
    }

    private GetInfoUser.CallBacks callBacks;

    public void setCallBacks(GetInfoUser.CallBacks callBacks) {
        this.callBacks = callBacks;
    }

    GetInfoUser(Context context) {
        this.context = context;
    }

    @Override
    protected List<InfoUser> doInBackground(Void... voids) {
        String url = "jdbc:mysql://" + context.getString(R.string.ip) + ":" + context.getString(R.string.port)
                + "/" + context.getString(R.string.dbName); // 构建连接mysql的字符串
        String user = context.getString(R.string.user_id);
        String password = context.getString(R.string.password);
        List<InfoUser> adminUserList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            Log.e(TAG, "远程连接成功!");
        } catch (SQLException e) {
            Log.e(TAG, "远程连接失败!");
        }
        if (conn != null) {
            String sql = "select * from info_user";
//                String sql_normal = "select * from normal_user where id = " + id;
            try {
                java.sql.Statement statement = (Statement) conn.createStatement();
                ResultSet rSet = statement.executeQuery(sql);
                while (rSet.next()) {
                    Log.e(TAG, rSet.getString("id"));
                    InfoUser tmpAdminUser = new InfoUser();
                    tmpAdminUser.setId(rSet.getString("id"));
                    tmpAdminUser.setSex(rSet.getString("sex"));
                    tmpAdminUser.setName(rSet.getString("name"));
                    tmpAdminUser.setPhone_number(rSet.getString("phone_number"));
                    tmpAdminUser.setRoom_number(rSet.getString("room_number"));
                    tmpAdminUser.setTotal_pay(Double.parseDouble(rSet.getString("total_pay")));
                    adminUserList.add(tmpAdminUser);
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
        return adminUserList;
    }

    @Override
    protected void onPostExecute(List<InfoUser> infoUserList) {
        super.onPostExecute(infoUserList);
        if (!infoUserList.isEmpty()) {
            callBacks.getInfoUsers(infoUserList);
        } else {
            Toast.makeText(context.getApplicationContext(), "数据表没有记录！", Toast.LENGTH_LONG).show();
        }
    }
}
