package com.example.remotelogin.MyMainActivity.AdminActivity.BoardFunction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remotelogin.R;

import java.util.ArrayList;
import java.util.List;

public class AdminUserCommentAdapter extends RecyclerView.Adapter<AdminUserCommentAdapter.CommentHolder> {

    private List<AdminUserComment> comments = new ArrayList<>();

    public void setComments(List<AdminUserComment> comments) {
        this.comments.clear();
        this.comments.addAll(comments);
    }

    @NonNull
    @Override
    public AdminUserCommentAdapter.CommentHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.comment_item,parent,false);
        return new AdminUserCommentAdapter.CommentHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdminUserCommentAdapter.CommentHolder holder, int position) {
        AdminUserComment acomment = comments.get(position);
        holder.name.setText(acomment.getName());
        holder.comm_time.setText(acomment.getTime());
        holder.comm_text.setText(acomment.getComm_text());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    static class CommentHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView comm_time;
        TextView comm_text;

        public CommentHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            comm_time = (TextView)itemView.findViewById(R.id.comm_time);
            comm_text = (TextView)itemView.findViewById(R.id.comment);
        }
    }
}