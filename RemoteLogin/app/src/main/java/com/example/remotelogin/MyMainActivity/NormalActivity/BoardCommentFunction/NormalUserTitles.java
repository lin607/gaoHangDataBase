package com.example.remotelogin.MyMainActivity.NormalActivity.BoardCommentFunction;

public class NormalUserTitles {
    String title;
    String board_id;

    public NormalUserTitles(String title, String board_id) {
        this.title = title;
        this.board_id = board_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBoard_id() {
        return board_id;
    }

    public void setBoard_id(String board_id) {
        this.board_id = board_id;
    }
}
