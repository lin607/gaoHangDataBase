package com.example.remotelogin.MyMainActivity.AdminActivity.PayFunction.PayDatabaseOperation;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanForms;
import com.example.remotelogin.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AddPayForms extends AsyncTask<List<PayForms>, Void, Void> {
    final private String TAG = "MYTAG";
    private Context context;

    public AddPayForms(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(List<PayForms>... payFormList) {
        List<PayForms> payForms = payFormList[0];
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
                for (int i = 0; i < payForms.size(); i++) {
                    String sql = "insert into info_clean_staff " +
                            "(post_cost_id, post_user_id, date, water_cost, elect_cost, done, should_pay, payed, unpay) " +
                            "values (" +
                            payForms.get(i).getPostCostId() + ", " +
                            payForms.get(i).getPostUserId() + ", '" +
                            payForms.get(i).getDate() + "', " +
                            payForms.get(i).getWaterCost() + ", " +
                            payForms.get(i).getElectCost() + ", " +
                            payForms.get(i).getDone() + ", " +
                            payForms.get(i).getShouldPay() + "," +
                            payForms.get(i).getPayed() + "," +
                            payForms.get(i).getUnpay() + ")";
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
        Toast.makeText(context.getApplicationContext(), "添加缴费表单成功", Toast.LENGTH_LONG).show();
    }
}
