package com.example.remotelogin.MyMainActivity.NormalActivity.NormalUserDetailInfo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.remotelogin.MyMainActivity.NormalActivity.ChangePasswordFunction.IDFindPwd;
import com.example.remotelogin.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IDFindNormalUser extends AsyncTask<String, Void, NormalUserDetail> {
    final private String TAG = "MYTAG";
    private Context context;

    public interface CallBacks {
        void getNormalUserDetail(NormalUserDetail normalUserDetail);
    }

    private IDFindNormalUser.CallBacks callBacks;

    public void setCallBacks(IDFindNormalUser.CallBacks callBacks) {
        this.callBacks = callBacks;
    }


    IDFindNormalUser(Context context) {
        this.context = context;
    }

    @Override
    protected NormalUserDetail doInBackground(String... strings) {
        String url = "jdbc:mysql://" + context.getString(R.string.ip) + ":" + context.getString(R.string.port)
                + "/" + context.getString(R.string.dbName); // 构建连接mysql的字符串
        String user = context.getString(R.string.user_id);
        String password = context.getString(R.string.password);
        NormalUserDetail normalUserDetail = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            Log.e(TAG, "远程连接成功!");
        } catch (SQLException e) {
            Log.e(TAG, "远程连接失败!");
        }
        if (conn != null) {
            String sql_normal = "select * from info_user where id = " + strings[0];
            try {
                java.sql.Statement statement = (Statement) conn.createStatement();
                ResultSet rSet = statement.executeQuery(sql_normal);
                while (rSet.next()) {
                    Log.e(TAG, rSet.getString("id"));
                    normalUserDetail.setId(rSet.getInt("id"));
                    normalUserDetail.setName(rSet.getString("name"));
                    normalUserDetail.setSex(rSet.getString("sex"));
                    normalUserDetail.setRoomNumber(rSet.getString("room_number"));
                    normalUserDetail.setPhoneNumber(rSet.getString("phone_number"));
                    normalUserDetail.setTotalPay(rSet.getFloat("total_pay"));
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
        return normalUserDetail;
    }

    @Override
    protected void onPostExecute(NormalUserDetail normalUserDetail) {
        super.onPostExecute(normalUserDetail);
        callBacks.getNormalUserDetail(normalUserDetail);
    }
}