package com.example.remotelogin.MyMainActivity.NormalActivity.ReportDeviceRepairFunction;

public class NormalUserDevice {
    String id;
    String type;

    public NormalUserDevice(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
