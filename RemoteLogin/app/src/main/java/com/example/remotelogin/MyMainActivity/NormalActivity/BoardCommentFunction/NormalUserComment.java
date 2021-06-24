package com.example.remotelogin.MyMainActivity.NormalActivity.BoardCommentFunction;

public class NormalUserComment {
    private String name;
    private String time;
    private String comm_text;
    private String id;

    public NormalUserComment(String name, String time, String comm_text) {
        this.name = name;
        this.time = time;
        this.comm_text = comm_text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComm_text() {
        return comm_text;
    }

    public void setComm_text(String comm_text) {
        this.comm_text = comm_text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
