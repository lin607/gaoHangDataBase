package com.example.remotelogin.MyMainActivity.NormalActivity.BoardCommentFunction;

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

public class NormalUserBoardAdapter extends RecyclerView.Adapter<NormalUserBoardAdapter.BoardHolder> {

    private List<NormalUserTitles> titles = new ArrayList<>();
    private Context context;
    private String name;
    private int id;

    public NormalUserBoardAdapter(Context context, String name, int id) {
        this.context = context;
        this.name = name;
        this.id = id;
    }

    public void setTitles(List<NormalUserTitles> titles) {
        this.titles.addAll(titles);
    }

    @NonNull
    @Override
    public BoardHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemview = layoutInflater.inflate(R.layout.board_cell,parent,false);
        return new BoardHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull final NormalUserBoardAdapter.BoardHolder holder, int position) {
        String atitle = titles.get(position).getTitle();
        holder.title.setText(atitle);
        holder.BoardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                String atitle = titles.get(position).getTitle();
                String board_id = titles.get(position).getBoard_id();
                Intent intent = new Intent(context,NormalUserContentActivity.class);
                intent.putExtra("Title",atitle);
                intent.putExtra("name", name);
                intent.putExtra("id", id);
                intent.putExtra("board_id",board_id);
                context.startActivity(intent);
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
            title = (TextView)itemView.findViewById(R.id.title);
            BoardView = itemView;
        }
    }
}
