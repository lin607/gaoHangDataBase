package com.example.remotelogin.MyMainActivity.NormalActivity.ReportDeviceRepairFunction;

public class NormalUserStaff {
    String id;
    String name;
    String score;
    String work;

    public NormalUserStaff(String id, String name, String score, String work) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.work = work;
    }

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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}