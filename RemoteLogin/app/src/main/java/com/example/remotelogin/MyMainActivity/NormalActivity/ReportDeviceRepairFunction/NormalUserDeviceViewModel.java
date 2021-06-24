package com.example.remotelogin.MyMainActivity.NormalActivity.ReportDeviceRepairFunction;

import android.app.Application;
import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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

public class NormalUserDeviceViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<NormalUserDevice>> alldevices = new MutableLiveData<>();
    private MutableLiveData<List<NormalUserStaff>> allstaffs = new MutableLiveData<>();
    private MutableLiveData<List<NormalUserReport>> allreports = new MutableLiveData<>();

    public NormalUserDeviceViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public MutableLiveData<List<NormalUserDevice>> getAlldevices() {
        return alldevices;
    }

    public MutableLiveData<List<NormalUserStaff>> getAllstaffs() {
        return allstaffs;
    }

    public MutableLiveData<List<NormalUserReport>> getAllreports() {
        return allreports;
    }

    void Reportrepair(String... strings) {
        new Reportrepair(context).execute(strings);
    }

    void Device_info() {
        new DeviceAsyncTask().execute();
    }

    void Staff_info() {
        new StaffAsyncTask().execute();
    }

    void Report_info(String... strings) {
        new ReportAsyncTask().execute(strings);
    }

    void Mark_repair(String... strings) {
        new MarkAsyncTask().execute(strings);
    }

    class Reportrepair extends AsyncTask<String, Void, Boolean> {
        final private String TAG = "MYTAG";
        private Context context;

        public Reportrepair(Context context) {
            this.context = context;
        }

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
                String sql1 = "UPDATE info_device SET " +
                        "need_repair = true," +
                        "staff_id = " + strings[2] +
                        " where id=" + strings[0];

                DateFormat DateFormat = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    DateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
                }
                Date date = new Date(System.currentTimeMillis());
                String time = DateFormat.format(date);
                String sql2 = "INSERT into report_for_repair (report_date, device_id,user_id,repaired,staff_id) values ('" +
                        time + "','" + strings[0] + "'," + strings[1] + ",0," + strings[2] + ")";

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

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                Toast.makeText(context, "报修成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class DeviceAsyncTask extends AsyncTask<String, Void, Boolean> {
        final private String TAG = "MYTAG";
        List<NormalUserDevice> devices = new ArrayList<>();

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
                String sql = "select id,type from info_device where need_repair = 0";

                try {
                    java.sql.Statement statement = (Statement) conn.createStatement();
                    ResultSet rSet = statement.executeQuery(sql);
                    while (rSet.next()) {
                        NormalUserDevice device = new NormalUserDevice(rSet.getString("id"), rSet.getString("type"));
                        devices.add(device);
                    }
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

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                alldevices.setValue(devices);
            }
        }
    }

    class StaffAsyncTask extends AsyncTask<Void, Void, Boolean> {
        final private String TAG = "MYTAG";
        List<NormalUserStaff> staffs = new ArrayList<>();

        @Override
        protected Boolean doInBackground(Void... voidss) {
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
                String sql = "select id,name,avg_score,work_times from info_repair_staff";

                try {
                    java.sql.Statement statement = (Statement) conn.createStatement();
                    ResultSet rSet = statement.executeQuery(sql);
                    while (rSet.next()) {
                        NormalUserStaff staff = new NormalUserStaff(rSet.getString("id"), rSet.getString("name"),
                                rSet.getString("avg_score"), rSet.getString("work_times"));
                        staffs.add(staff);
                    }
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

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                allstaffs.setValue(staffs);
            }
        }
    }

    class ReportAsyncTask extends AsyncTask<String, Void, Boolean> {
        final private String TAG = "MYTAG";
        List<NormalUserReport> reports = new ArrayList<>();

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
                String sql1 = "select id,report_date,device_id,repaired,finish_date,staff_id,score,finish_date" +
                        " from report_for_repair where user_id = " + strings[0];
                String sql2 = "select name,phone_number from info_user where id = " + strings[0];

                try {
                    java.sql.Statement statement1 = (Statement) conn.createStatement();
                    java.sql.Statement statement2 = (Statement) conn.createStatement();
                    java.sql.Statement statement3 = (Statement) conn.createStatement();
                    java.sql.Statement statement4 = (Statement) conn.createStatement();
                    ResultSet rSet1 = statement1.executeQuery(sql1);
                    ResultSet rSet2 = statement2.executeQuery(sql2);
                    rSet2.next();
                    while (rSet1.next()) {
                        String staff_id = rSet1.getString("staff_id");
                        String sql3 = "select name,phone_number from info_repair_staff where id = " + staff_id;
                        ResultSet rSet3 = statement3.executeQuery(sql3);
                        rSet3.next();
                        String sql4 = "select type from info_device where id = " + rSet1.getString("device_id");
                        ResultSet rSet4 = statement4.executeQuery(sql4);
                        rSet4.next();
                        NormalUserDevice device = new NormalUserDevice(rSet1.getString("device_id"), rSet4.getString("type"));
                        String finish_date = rSet1.getString("finish_date");
                        if (finish_date != null) {
                            finish_date = finish_date.substring(0, 11);
                        } else {
                            finish_date = " ";
                        }
                        NormalUserReport report = new NormalUserReport(rSet1.getString("id"),
                                rSet1.getString("report_date").substring(0, 11) + "~ "
                                        + finish_date,
                                device, rSet3.getString("name"), rSet3.getString("phone_number"),
                                rSet1.getString("repaired"), rSet2.getString("name"),
                                rSet2.getString("phone_number"), rSet1.getString("score"));
                        reports.add(report);
                        rSet3.close();
                        rSet4.close();
                    }
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

    class MarkAsyncTask extends AsyncTask<String, Void, Boolean> {
        final private String TAG = "MYTAG";
        List<NormalUserReport> reports = new ArrayList<>();

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
                String sql1 = "UPDATE report_for_repair set score = " + strings[1] + " where id = " + strings[0];
                String sql2 = "UPDATE info_repair_staff set work_times = work_times+1,avg_score = (avg_score*(work_times-1)+" +
                        strings[1] + ")/work_times where id = (select staff_id from report_for_repair where id = " + strings[0] + ")";

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

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                allreports.setValue(reports);
            }
        }
    }
}