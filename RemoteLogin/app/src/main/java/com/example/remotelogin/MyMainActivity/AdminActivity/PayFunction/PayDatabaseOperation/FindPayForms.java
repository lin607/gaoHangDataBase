package com.example.remotelogin.MyMainActivity.AdminActivity.PayFunction.PayDatabaseOperation;

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

public class FindPayForms extends AsyncTask<Void, Void, List<PayForms>> {
    final private String TAG = "MYTAG";
    private Context context;

    public interface CallBacks {
        void getAllPayForms(List<PayForms> payForms);
    }

    private FindPayForms.CallBacks callBacks;

    public void setCallBacks(FindPayForms.CallBacks callBacks) {
        this.callBacks = callBacks;
    }

    public FindPayForms(Context context) {
        this.context = context;
    }

    @Override
    protected List<PayForms> doInBackground(Void... voids) {
        String url = "jdbc:mysql://" + context.getString(R.string.ip) + ":" + context.getString(R.string.port)
                + "/" + context.getString(R.string.dbName); // 构建连接mysql的字符串
        String user = context.getString(R.string.user_id);
        String password = context.getString(R.string.password);
        List<PayForms> payFormsList = new ArrayList<>();
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
                    PayForms payForms = new PayForms();
                    payForms.setPostCostId(rSet.getInt("post_cost_id"));
                    payForms.setPostUserId(rSet.getInt("post_user_id"));
                    payForms.setDate(rSet.getDate("date"));
                    payForms.setWaterCost(rSet.getFloat("water_cost"));
                    payForms.setElectCost(rSet.getFloat("elect_cost"));
                    payForms.setDone(rSet.getBoolean("done"));
                    payForms.setShouldPay(rSet.getFloat("should_pay"));
                    payForms.setPayed(rSet.getFloat("payed"));
                    payForms.setUnpay(rSet.getFloat("unpay"));
                    payFormsList.add(payForms);
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
        return payFormsList;
    }

    @Override
    protected void onPostExecute(List<PayForms> list) {
        super.onPostExecute(list);
        if (!list.isEmpty()) {
            callBacks.getAllPayForms(list);
        } else {
            Toast.makeText(context.getApplicationContext(), "没有缴费记录！", Toast.LENGTH_LONG).show();
        }
    }
}