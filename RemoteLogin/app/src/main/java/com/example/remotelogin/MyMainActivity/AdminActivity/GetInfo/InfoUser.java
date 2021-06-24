package com.example.remotelogin.MyMainActivity.AdminActivity.GetInfo;

public class InfoUser {
    String name;
    String sex;
    String room_number;
    String phone_number;
    double total_pay;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public double getTotal_pay() {
        return total_pay;
    }

    public void setTotal_pay(double total_pay) {
        this.total_pay = total_pay;
    }
}
