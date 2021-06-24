package com.example.remotelogin.MyMainActivity.NormalActivity.BoardCommentFunction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remotelogin.R;

import java.util.ArrayList;
import java.util.List;

public class NormalUserCommentAdapter  extends RecyclerView.Adapter<NormalUserCommentAdapter.CommentHolder> {

    private List<NormalUserComment> comments = new ArrayList<>();

    public void setComments(List<NormalUserComment> comments) {
        this.comments.clear();
        this.comments.addAll(comments);
    }

    @NonNull
    @Override
    public NormalUserCommentAdapter.CommentHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.comment_item,parent,false);
        return new NormalUserCommentAdapter.CommentHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull final NormalUserCommentAdapter.CommentHolder holder, int position) {
        NormalUserComment acomment = comments.get(position);
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
