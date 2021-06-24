package com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction;

import java.sql.Date;

/**
 * 保洁清单
 */
public class CleanForms {
    protected int id;
    protected Date cleanDate;
    protected int cleanMachineId;
    protected int cleanUserId;

    public CleanForms() {
    }

    /**
     * Get 保洁单的编号
     */
    public int getId() {
        return id;
    }

    /**
     * Set 保洁单的编号
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Get 保洁日期
     */
    public Date getCleanDate() {
        return cleanDate;
    }

    /**
     * Set 保洁日期
     */
    public void setCleanDate(Date value) {
        this.cleanDate = value;
    }

    /**
     * Get 设备id
     */
    public int getCleanMachineId() {
        return cleanMachineId;
    }

    /**
     * Set 设备id
     */
    public void setCleanMachineId(int value) {
        this.cleanMachineId = value;
    }

    /**
     * Get 保洁人员id
     */
    public int getCleanUserId() {
        return cleanUserId;
    }

    /**
     * Set 保洁人员id
     */
    public void setCleanUserId(int value) {
        this.cleanUserId = value;
    }

    public void reset() {
        id = 0;
        cleanDate = null;
        cleanMachineId = 0;
        cleanUserId = 0;
    }

    public void assignFrom(CleanForms AObj) {
        if (AObj == null) {
            reset();
            return;
        }
        id = AObj.id;
        cleanDate = AObj.cleanDate;
        cleanMachineId = AObj.cleanMachineId;
        cleanUserId = AObj.cleanUserId;
    }
}