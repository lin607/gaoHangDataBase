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
import java.util.ArrayList;
import java.util.List;

public class IDFindRecord extends AsyncTask<String, Void, List<PostCostElement>> {
    final private String TAG = "MYTAG";
    private Context context;

    public interface CallBacks {
        void getPayRecord(List<PostCostElement> postCostElements);
    }

    private IDFindRecord.CallBacks callBacks;

    public void setCallBacks(IDFindRecord.CallBacks callBacks) {
        this.callBacks = callBacks;
    }


    IDFindRecord(Context context) {
        this.context = context;
    }

    @Override
    protected List<PostCostElement> doInBackground(String... strings) {
        String url = "jdbc:mysql://" + context.getString(R.string.ip) + ":" + context.getString(R.string.port)
                + "/" + context.getString(R.string.dbName); // 构建连接mysql的字符串
        String user = context.getString(R.string.user_id);
        String password = context.getString(R.string.password);
        List<PostCostElement> postCostElements = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            Log.e(TAG, "远程连接成功!");
        } catch (SQLException e) {
            Log.e(TAG, "远程连接失败!");
        }
        if (conn != null) {
            String sql_normal = "select * from post_cost where post_user_id = " + strings[0];
            try {
                java.sql.Statement statement = (Statement) conn.createStatement();
                ResultSet rSet = statement.executeQuery(sql_normal);
                while (rSet.next()) {
                    PostCostElement postCostElement = new PostCostElement();
                    postCostElement.setPostCostId(rSet.getInt("post_cost_id"));
                    postCostElement.setPostUserId(rSet.getInt("post_user_id"));
                    postCostElement.setDate(rSet.getDate("date"));
                    postCostElement.setWaterCost(rSet.getDouble("water_cost"));
                    postCostElement.setElectCost(rSet.getDouble("elect_cost"));
                    postCostElement.setDone(rSet.getBoolean("done"));
                    postCostElement.setShouldPay(rSet.getDouble("should_pay"));
                    postCostElement.setPayed(rSet.getDouble("payed"));
                    postCostElement.setUnpay(rSet.getDouble("unpay"));
                    postCostElements.add(postCostElement);
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
        return postCostElements;
    }

    @Override
    protected void onPostExecute(List<PostCostElement> postCostElements) {
        super.onPostExecute(postCostElements);
        if (!postCostElements.isEmpty()){
            callBacks.getPayRecord(postCostElements);
        }else {
            Toast.makeText(context, "无记录", Toast.LENGTH_LONG).show();
        }
    }
}