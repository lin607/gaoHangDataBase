package com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction;

import java.sql.Date;

/**
 * 保洁人员信息
 */
public class CleanStaffInfo {
    protected int id;
    protected String name;
    protected Date hireDate;
    protected String phoneNumber;
    protected String sex;

    public CleanStaffInfo() {
    }

    /**
     * Get 保洁人员id
     */
    public int getId() {
        return id;
    }

    /**
     * Set 保洁人员id
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Get 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * Set 姓名
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Get 雇用日期
     */
    public Date getHireDate() {
        return hireDate;
    }

    /**
     * Set 雇用日期
     */
    public void setHireDate(Date value) {
        this.hireDate = value;
    }

    /**
     * Get 手机号
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set 手机号
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
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

    public void reset() {
        id = 0;
        name = null;
        hireDate = null;
        phoneNumber = null;
        sex = null;
    }

    public void assignFrom(CleanStaffInfo AObj) {
        if (AObj == null) {
            reset();
            return;
        }
        id = AObj.id;
        name = AObj.name;
        hireDate = AObj.hireDate;
        phoneNumber = AObj.phoneNumber;
        sex = AObj.sex;
    }

}