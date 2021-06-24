package com.example.remotelogin.Login;

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

class FindAdminUser extends AsyncTask<Void, Void, AdminUser> {
    final private String TAG = "MYTAG";
    private String id;
    private Context context;

    public interface CallBacks{
        void getAdmin(AdminUser adminUser);
    }

    private CallBacks callBacks;

    public void setCallBacks(CallBacks callBacks){
        this.callBacks = callBacks;
    }

    FindAdminUser(String id, Context context) {
        this.id = id;
        this.context = context;
    }

    @Override
    protected AdminUser doInBackground(Void... voids) {
        String url = "jdbc:mysql://" + context.getString(R.string.ip) + ":" + context.getString(R.string.port)
                + "/" + context.getString(R.string.dbName); // 构建连接mysql的字符串
        String user = context.getString(R.string.user_id);
        String password = context.getString(R.string.password);
        AdminUser adminUser = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            Log.e(TAG, "远程连接成功!");
        } catch (SQLException e) {
            Log.e(TAG, "远程连接失败!");
        }

        if (conn != null) {
            String sql = "select * from admin_user where id = " + id;
//                String sql_normal = "select * from normal_user where id = " + id;
            try {
                java.sql.Statement statement = (Statement) conn.createStatement();
                ResultSet rSet = statement.executeQuery(sql);
                while (rSet.next()) {
                    Log.e(TAG, rSet.getString("id"));
                    adminUser = new AdminUser();
                    adminUser.setId(Integer.parseInt(rSet.getString("id")));
                    adminUser.setName(rSet.getString("name"));
                    adminUser.setPhone_number(rSet.getString("phone_number"));
                    adminUser.setPassword(rSet.getString("password"));
                }
                statement.close();
                rSet.close();
            } catch (SQLException e) {
                Log.e(TAG, "creatStatement error!");
            }
//                try {
//                    java.sql.Statement statement = (Statement) conn.createStatement();
//                    ResultSet rSet = statement.executeQuery(sql_normal);
//                    while (rSet.next()) {
//                        Log.e(TAG, rSet.getString("id"));
//                        normalUser = new NormalUser();
//                        normalUser.setId(Integer.parseInt(rSet.getString("id")));
//                        normalUser.setName(rSet.getString("name"));
//                        normalUser.setPhone_number(rSet.getString("phone_number"));
//                        normalUser.setPassword(rSet.getString("password"));
//                        normalUser.setUser_id(rSet.getString("user_id"));
//                    }
//                    statement.close();
//                    rSet.close();
//                } catch (SQLException e) {
//                    Log.e(TAG, "creatStatement error!");
//                }
        }
        try {
            conn.close();
        } catch (SQLException e) {
            Log.e(TAG, "关闭连接失败!");
        }
        return adminUser;
    }

    @Override
    protected void onPostExecute(AdminUser adminUser) {
        super.onPostExecute(adminUser);
        callBacks.getAdmin(adminUser);
//        if (adminUser != null) {
//            callBacks.getAdmin(adminUser);
//        } else {
//            Toast.makeText(context.getApplicationContext(), "找不到匹配管理员用户！", Toast.LENGTH_LONG).show();
//        }
    }

}