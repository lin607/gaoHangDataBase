package com.example.remotelogin.MyMainActivity.AdminActivity.DeviceFunction;

public class AdminDeviceReport {
    String report_id;
    String date;
    AdminDevice device;
    String staff_name;
    String staff_phone;
    String finish;
    String user_name;
    String user_phone;
    String score;

    public AdminDeviceReport(String report_id, String date, AdminDevice device, String staff_name,
                  String staff_phone, String finish, String user_name, String user_phone, String score) {
        this.report_id = report_id;
        this.date = date;
        this.device = device;
        this.staff_name = staff_name;
        this.staff_phone = staff_phone;
        this.finish = finish;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.score = score;
    }

    public String getReport_id() {
        return report_id;
    }

    public String getDate() {
        return date;
    }

    public AdminDevice getDevice() {
        return device;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public String getStaff_phone() {
        return staff_phone;
    }

    public String getFinish() {
        return finish;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public String getScore() {
        return score;
    }
}
