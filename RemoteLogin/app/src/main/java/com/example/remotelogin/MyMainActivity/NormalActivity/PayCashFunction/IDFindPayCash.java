package com.example.remotelogin.MyMainActivity.NormalActivity.PayCashFunction;

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

public class IDFindPayCash extends AsyncTask<String, Void, Double> {
    final private String TAG = "MYTAG";
    private Context context;

    public interface CallBacks {
        void getPayCash(double cash);
    }

    private IDFindPayCash.CallBacks callBacks;

    public void setCallBacks(IDFindPayCash.CallBacks callBacks) {
        this.callBacks = callBacks;
    }


    IDFindPayCash(Context context) {
        this.context = context;
    }

    @Override
    protected Double doInBackground(String... strings) {
        String url = "jdbc:mysql://" + context.getString(R.string.ip) + ":" + context.getString(R.string.port)
                + "/" + context.getString(R.string.dbName); // 构建连接mysql的字符串
        String user = context.getString(R.string.user_id);
        String password = context.getString(R.string.password);
        String pay_cash = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            Log.e(TAG, "远程连接成功!");
        } catch (SQLException e) {
            Log.e(TAG, "远程连接失败!");
        }
        if (conn != null) {
            String sql_normal = "select unpay from post_cost where post_user_id = " + strings[0];
            try {
                java.sql.Statement statement = (Statement) conn.createStatement();
                ResultSet rSet = statement.executeQuery(sql_normal);
                while (rSet.next()) {
                    Log.e(TAG, rSet.getString("unpay"));
                    pay_cash = rSet.getBigDecimal("unpay").toString();
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
        if (pay_cash != null) {
            return Double.parseDouble(pay_cash);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Double doubles) {
        super.onPostExecute(doubles);
        if (doubles != null) {
            callBacks.getPayCash(doubles);
        } else {
            Toast.makeText(context, "无需缴费", Toast.LENGTH_LONG).show();
        }
    }
}