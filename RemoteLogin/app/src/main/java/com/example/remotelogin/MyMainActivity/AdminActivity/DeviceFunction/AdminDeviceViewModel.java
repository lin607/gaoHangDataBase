package com.example.remotelogin.MyMainActivity.AdminActivity.DeviceFunction;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.remotelogin.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminDeviceViewModel extends AndroidViewModel {
    private MutableLiveData<List<AdminDeviceReport>> allreports = new MutableLiveData<>();
    private Context context;

    public AdminDeviceViewModel(@NonNull Application application) {
        super(application);

    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public MutableLiveData<List<AdminDeviceReport>> getAllreports() {
        return allreports;
    }


    void Report_info(String... strings) {
        new ReportAsyncTask().execute(strings);
    }

    void Finish_repair(String... strings) {
        new FinishAsyncTask().execute(strings);
    }

    class ReportAsyncTask extends AsyncTask<String, Void, Boolean> {
        final private String TAG = "MYTAG";
        List<AdminDeviceReport> reports = new ArrayList<>();

        @Override
        protected Boolean doInBackground(String... strings) {
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
                return false;
            }

            if (conn != null) {
                String sql1 = "select id,report_date,device_id,repaired,finish_date,staff_id,score,finish_date,user_id" +
                        " from report_for_repair";

                try {
                    java.sql.Statement statement1 = (Statement) conn.createStatement();
                    java.sql.Statement statement2 = (Statement) conn.createStatement();
                    java.sql.Statement statement3 = (Statement) conn.createStatement();
                    java.sql.Statement statement4 = (Statement) conn.createStatement();
                    ResultSet rSet1 = statement1.executeQuery(sql1);
                    while (rSet1.next()) {
                        String user_id = rSet1.getString("user_id");
                        String sql2 = "select name,phone_number from info_user where id = " + user_id;
                        ResultSet rSet2 = statement2.executeQuery(sql2);
                        rSet2.next();
                        String staff_id = rSet1.getString("staff_id");
                        String sql3 = "select name,phone_number from info_repair_staff where id = " + staff_id;
                        ResultSet rSet3 = statement3.executeQuery(sql3);
                        rSet3.next();
                        String sql4 = "select type from info_device where id = " + rSet1.getString("device_id");
                        ResultSet rSet4 = statement4.executeQuery(sql4);
                        rSet4.next();
                        AdminDevice device = new AdminDevice(rSet1.getString("device_id"), rSet4.getString("type"));
                        String finish_date = rSet1.getString("finish_date");
                        if (finish_date != null) {
                            finish_date = finish_date.substring(0, 11);
                        } else {
                            finish_date = " ";
                        }
                        AdminDeviceReport report = new AdminDeviceReport(rSet1.getString("id"),
                                rSet1.getString("report_date").substring(0, 11) + "~ "
                                        + finish_date,
                                device, rSet3.getString("name"), rSet3.getString("phone_number"),
                                rSet1.getString("repaired"), rSet2.getString("name"),
                                rSet2.getString("phone_number"), rSet1.getString("score"));
                        reports.add(report);
                        rSet2.close();
                        rSet3.close();
                        rSet4.close();
                    }
                    rSet1.close();
                    statement1.close();
                    statement2.close();
                    statement3.close();
                    statement4.close();
                } catch (SQLException e) {
                    Log.e(TAG, "creatStatement error!");
                    return false;
                }
                try {
                    conn.close();
                } catch (SQLException e) {
                    Log.e(TAG, "关闭连接失败!");
                    return false;
                }
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                allreports.setValue(reports);
            }
        }
    }

    class FinishAsyncTask extends AsyncTask<String, Void, Boolean> {
        final private String TAG = "MYTAG";

        @Override
        protected Boolean doInBackground(String... strings) {
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
                return false;
            }

            if (conn != null) {
                DateFormat DateFormat = null;
                // todo 有bug
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    DateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
                }
                Date date = new Date(System.currentTimeMillis());
                String time = DateFormat.format(date);
                String sql1 = "UPDATE report_for_repair set repaired = 1,finish_date = '" + time + "' where id = " + strings[0];
                String sql2 = "UPDATE info_device set need_repair = 0 where id = (select device_id from report_for_repair where id = "+strings[0]+")";

                try {
                    java.sql.Statement statement = (Statement) conn.createStatement();
                    statement.executeUpdate(sql1);
                    statement.executeUpdate(sql2);
                    statement.close();
                } catch (SQLException e) {
                    Log.e(TAG, "creatStatement error!");
                    return false;
                }
                try {
                    conn.close();
                } catch (SQLException e) {
                    Log.e(TAG, "关闭连接失败!");
                    return false;
                }
            }
            return true;
        }
    }
}