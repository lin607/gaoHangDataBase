package com.example.remotelogin.MyMainActivity.NormalActivity.NormalUserDetailInfo;

/**
 * 详细信息
 */
public class NormalUserDetail {
    protected int id;
    protected String name;
    protected String sex;
    protected String roomNumber;
    protected String phoneNumber;
    protected double totalPay;

    public NormalUserDetail() {
    }

    /**
     * Get 用户id
     */
    public int getId() {
        return id;
    }

    /**
     * Set 用户id
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Get 用户姓名
     */
    public String getName() {
        return name;
    }

    /**
     * Set 用户姓名
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Get 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * Set 性别
     */
    public void setSex(String value) {
        this.sex = value;
    }

    /**
     * Get 房间号
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * Set 房间号
     */
    public void setRoomNumber(String value) {
        this.roomNumber = value;
    }

    /**
     * Get 手机号码
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set 手机号码
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    /**
     * Get 总交费用
     */
    public double getTotalPay() {
        return totalPay;
    }

    /**
     * Set 总交费用
     */
    public void setTotalPay(double value) {
        this.totalPay = value;
    }

    public void reset() {
        id = 0;
        name = null;
        sex = null;
        roomNumber = null;
        phoneNumber = null;
        totalPay = 0;
    }

    public void assignFrom(NormalUserDetail AObj) {
        if (AObj == null) {
            reset();
            return;
        }
        id = AObj.id;
        name = AObj.name;
        sex = AObj.sex;
        roomNumber = AObj.roomNumber;
        phoneNumber = AObj.phoneNumber;
        totalPay = AObj.totalPay;
    }

}