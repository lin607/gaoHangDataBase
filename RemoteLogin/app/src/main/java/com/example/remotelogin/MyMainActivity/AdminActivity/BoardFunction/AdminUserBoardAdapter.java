package com.example.remotelogin.MyMainActivity.AdminActivity.BoardFunction;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remotelogin.R;

import java.util.ArrayList;
import java.util.List;

public class AdminUserBoardAdapter extends RecyclerView.Adapter<AdminUserBoardAdapter.BoardHolder> {

    private List<AdminUserTitle> titles = new ArrayList<>();
    private Context context;

    public AdminUserBoardAdapter(Context context) {
        this.context = context;
    }

    public void setTitles(List<AdminUserTitle> titles) {
        this.titles.clear();
        this.titles.addAll(titles);
    }

    @NonNull
    @Override
    public BoardHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.board_cell, parent, false);
        return new BoardHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdminUserBoardAdapter.BoardHolder holder, int position) {
        String atitle = titles.get(position).getTitle();
        holder.title.setText(atitle);
        holder.BoardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                String atitle = titles.get(position).getTitle();
                String board_id = titles.get(position).getBoard_id();
                Intent intent = new Intent(context, AdminUserContentActivity.class);
                intent.putExtra("Title", atitle);
                intent.putExtra("board_id",board_id);
                ((AdminUserBoardMainActivity) context).startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    static class BoardHolder extends RecyclerView.ViewHolder {
        TextView title;
        View BoardView;

        public BoardHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            BoardView = itemView;
        }
    }
}
