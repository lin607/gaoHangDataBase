package com.example.remotelogin.MyMainActivity.NormalActivity.PayCashFunction;

import java.sql.Date;

/**
 * 费用
 */
public class PostCostElement {
    protected int postCostId;
    protected int postUserId;
    protected Date date;
    protected double waterCost;
    protected double electCost;
    protected boolean done;
    protected double shouldPay;
    protected double payed;
    protected double unpay;

    public PostCostElement() {
    }

    /**
     * Get 费用流水编号
     */
    public int getPostCostId() {
        return postCostId;
    }

    /**
     * Set 费用流水编号
     */
    public void setPostCostId(int value) {
        this.postCostId = value;
    }

    /**
     * Get 用户id
     */
    public int getPostUserId() {
        return postUserId;
    }

    /**
     * Set 用户id
     */
    public void setPostUserId(int value) {
        this.postUserId = value;
    }

    /**
     * Get 费用日期 月结
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set 费用日期 月结
     */
    public void setDate(Date value) {
        this.date = value;
    }

    /**
     * Get 水费
     */
    public double getWaterCost() {
        return waterCost;
    }

    /**
     * Set 水费
     */
    public void setWaterCost(double value) {
        this.waterCost = value;
    }

    /**
     * Get 电费
     */
    public double getElectCost() {
        return electCost;
    }

    /**
     * Set 电费
     */
    public void setElectCost(double value) {
        this.electCost = value;
    }

    /**
     * Get 是否交了
     */
    public boolean getDone() {
        return done;
    }

    /**
     * Set 是否交了
     */
    public void setDone(boolean value) {
        this.done = value;
    }

    /**
     * Get 应交金额
     */
    public double getShouldPay() {
        return shouldPay;
    }

    /**
     * Set 应交金额
     */
    public void setShouldPay(double value) {
        this.shouldPay = value;
    }

    /**
     * Get 已交金额
     */
    public double getPayed() {
        return payed;
    }

    /**
     * Set 已交金额
     */
    public void setPayed(double value) {
        this.payed = value;
    }

    /**
     * Get 欠额
     */
    public double getUnpay() {
        return unpay;
    }

    /**
     * Set 欠额
     */
    public void setUnpay(double value) {
        this.unpay = value;
    }

    public void reset() {
        postCostId = 0;
        postUserId = 0;
        date = null;
        waterCost = 0;
        electCost = 0;
        done = false;
        shouldPay = 0;
        payed = 0;
        unpay = 0;
    }

    public void assignFrom(PostCostElement AObj) {
        if (AObj == null) {
            return;
        }
        postCostId = AObj.postCostId;
        postUserId = AObj.postUserId;
        date = AObj.date;
        waterCost = AObj.waterCost;
        electCost = AObj.electCost;
        done = AObj.done;
        shouldPay = AObj.shouldPay;
        payed = AObj.payed;
        unpay = AObj.unpay;
    }

}