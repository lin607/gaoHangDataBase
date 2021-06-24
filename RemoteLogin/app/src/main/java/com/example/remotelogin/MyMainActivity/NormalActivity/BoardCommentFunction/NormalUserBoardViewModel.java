package com.example.remotelogin.MyMainActivity.NormalActivity.BoardCommentFunction;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.remotelogin.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NormalUserBoardViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<NormalUserTitles>> alltitles = new MutableLiveData<>();
    private MutableLiveData<String> content = new MutableLiveData<>();
    private MutableLiveData<String> content_time = new MutableLiveData<>();
    private MutableLiveData<List<NormalUserComment>> allcomments = new MutableLiveData<>();
    private String BId;

    public NormalUserBoardViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public MutableLiveData<List<NormalUserTitles>> getTitles() {
        return alltitles;
    }

    public MutableLiveData<String> getContent() {
        return content;
    }

    public MutableLiveData<String> getContent_time() {
        return content_time;
    }

    public MutableLiveData<List<NormalUserComment>> getComments() {
        return allcomments;
    }

    void Board_titles() {
        new TitlesAsyncTask().execute();
    }

    void Board_content(String title) {
        new ContentAsyncTask().execute(title);
    }

    void Board_comment() {
        new CommentAsyncTask().execute();
    }

    void Board_addcomment(NormalUserComment comment) {
        new AddCommentAsyncTask().execute(comment);
    }

    public class TitlesAsyncTask extends AsyncTask<Void, Void, Boolean> {
        final private String TAG = "MYTAG";
        List<NormalUserTitles> titles = new ArrayList<>();

        @Override
        protected Boolean doInBackground(Void... voids) {
            String url = "jdbc:mysql://" + context.getString(R.string.ip) + ":" + context.getString(R.string.port)
                    + "/" + context.getString(R.string.dbName); // 构建连接mysql的字符串
            String user = context.getString(R.string.user_id);
            String password = context.getString(R.string.password);
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, user, password);
                Log.e(TAG, "远程连接成功!");
            } catch (SQLException e) {
                Log.e(TAG, "远程连接失败!");
                return false;
            }

            if (conn != null) {
                String sql = "select title,id from Board order by publish_time desc";

                try {
                    java.sql.Statement statement = (Statement) conn.createStatement();
                    ResultSet rSet = statement.executeQuery(sql);
                    while (rSet.next()) {
                        NormalUserTitles title = new NormalUserTitles(rSet.getString("title"),rSet.getString("id"));
                        titles.add(title);
                    }
                    Log.e(TAG, "查询成功");
                    rSet.close();
                    statement.close();
                } catch (SQLException e) {
                    Log.e(TAG, "creatStatement error!");
                    return false;
                }
                try {
                    conn.close();
                } catch (SQLException e) {
                    Log.e(TAG, "关闭连接失败!");
                    return false;
                }
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result)
                alltitles.setValue(titles);
        }
    }

    public class ContentAsyncTask extends AsyncTask<String, Void, Boolean> {
        final private String TAG = "MYTAG";
        String acontent;
        String ptime;

        @Override
        protected Boolean doInBackground(String... strings) {
            String url = "jdbc:mysql://" + context.getString(R.string.ip) + ":" + context.getString(R.string.port)
                    + "/" + context.getString(R.string.dbName); // 构建连接mysql的字符串
            String user = context.getString(R.string.user_id);
            String password = context.getString(R.string.password);
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, user, password);
                Log.e(TAG, "远程连接成功!");
            } catch (SQLException e) {
                Log.e(TAG, "远程连接失败!");
                return false;
            }

            if (conn != null) {
                String sql = "select content,publish_time from Board where id = "+strings[0];

                try {
                    java.sql.Statement statement = (Statement) conn.createStatement();
                    ResultSet rSet = statement.executeQuery(sql);
                    rSet.next();
                    acontent = rSet.getString("content");
                    ptime = rSet.getString("publish_time");
                    BId = strings[0];
                    rSet.close();
                    statement.close();
                } catch (SQLException e) {
                    Log.e(TAG, "creatStatement error!");
                    return false;
                }
                try {
                    conn.close();
                } catch (SQLException e) {
                    Log.e(TAG, "关闭连接失败!");
                    return false;
                }
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result) {
                content.setValue(acontent);
                content_time.setValue(ptime);
            }
        }
    }

    public class CommentAsyncTask extends AsyncTask<Void, Void, Boolean> {
        final private String TAG = "MYTAG";
        List<NormalUserComment> comments = new ArrayList<>();

        @Override
        protected Boolean doInBackground(Void... voids) {
            String url = "jdbc:mysql://" + context.getString(R.string.ip) + ":" + context.getString(R.string.port)
                    + "/" + context.getString(R.string.dbName); // 构建连接mysql的字符串
            String user = context.getString(R.string.user_id);
            String password = context.getString(R.string.password);
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, user, password);
                Log.e(TAG, "远程连接成功!");
            } catch (SQLException e) {
                Log.e(TAG, "远程连接失败!");
                return false;
            }

            if (conn != null) {
                String sql1 = "select user_id,comment_date,content from comment where board_id = " + BId +" order by comment_date desc";

                try {
                    java.sql.Statement statement1 = (Statement) conn.createStatement();
                    java.sql.Statement statement2 = (Statement) conn.createStatement();
                    ResultSet rSet1 = statement1.executeQuery(sql1);
                    while (rSet1.next()) {
                        String sql2 = "select name from normal_user where id =" + rSet1.getString("user_id");
                        ResultSet rSet2 = statement2.executeQuery(sql2);
                        rSet2.next();
                        NormalUserComment comment = new NormalUserComment(rSet2.getString("name"),
                                rSet1.getString("comment_date").substring(0, 19),
                                rSet1.getString("content"));
                        comments.add(comment);
                        rSet2.close();
                    }
                    rSet1.close();
                    statement1.close();
                    statement2.close();
                } catch (SQLException e) {
                    Log.e(TAG, "creatStatement error!");
                    return false;
                }
                try {
                    conn.close();
                } catch (SQLException e) {
                    Log.e(TAG, "关闭连接失败!");
                    return false;
                }
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result)
                allcomments.setValue(comments);
        }
    }

    public class AddCommentAsyncTask extends AsyncTask<NormalUserComment, Void, Boolean> {
        final private String TAG = "MYTAG";
        List<NormalUserComment> comments = new ArrayList<>();

        @Override
        protected Boolean doInBackground(NormalUserComment... comments) {
            String url = "jdbc:mysql://" + context.getString(R.string.ip) + ":" + context.getString(R.string.port)
                    + "/" + context.getString(R.string.dbName); // 构建连接mysql的字符串
            String user = context.getString(R.string.user_id);
            String password = context.getString(R.string.password);
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, user, password);
                Log.e(TAG, "远程连接成功!");
            } catch (SQLException e) {
                Log.e(TAG, "远程连接失败!");
                return false;
            }

            if (conn != null) {
                DateFormat DateFormat = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    DateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
                }
                Date date = new Date(System.currentTimeMillis());
                String time = DateFormat.format(date);
                String sql = "insert into comment (comment_date,user_id,content,board_id) values ('"
                        + time + "'," + comments[0].getId() + ",'" + comments[0].getComm_text() + "',"+BId+")";

                try {
                    java.sql.Statement statement = (Statement) conn.createStatement();
                    statement.executeUpdate(sql);
                    statement.close();
                } catch (SQLException e) {
                    Log.e(TAG, "creatStatement error!");
                    return false;
                }
                try {
                    conn.close();
                } catch (SQLException e) {
                    Log.e(TAG, "关闭连接失败!");
                    return false;
                }
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result)
                allcomments.setValue(comments);
        }
    }
}
