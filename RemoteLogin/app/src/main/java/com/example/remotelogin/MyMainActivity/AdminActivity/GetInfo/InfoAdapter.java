package com.example.remotelogin.MyMainActivity.AdminActivity.GetInfo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remotelogin.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {
    private List<InfoUser> infoUserList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView idTextView;
        TextView sexTextView;
        TextView phoneNumberTextView;
        TextView roomNumberTextView;
        TextView totalPayTextView;
        TextView nameTextView;

        public ViewHolder(View view) {
            super(view);
            idTextView = view.findViewById(R.id.id_text_view);
            sexTextView = view.findViewById(R.id.sex_text_view);
            phoneNumberTextView = view.findViewById(R.id.phone_number_text_view);
            roomNumberTextView = view.findViewById(R.id.room_number_text_view);
            totalPayTextView = view.findViewById(R.id.total_pay_text_view);
            nameTextView = view.findViewById(R.id.name_text_view);
        }
    }

    public InfoAdapter(List<InfoUser> infoUserList) {
        this.infoUserList = infoUserList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_user_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        InfoUser infoUser = infoUserList.get(position);
        holder.idTextView.setText(infoUser.getId());
        holder.sexTextView.setText(infoUser.getSex());
        holder.phoneNumberTextView.setText(infoUser.getPhone_number());
        holder.roomNumberTextView.setText(infoUser.getRoom_number());
        holder.totalPayTextView.setText(String.valueOf(infoUser.getTotal_pay()));
        holder.nameTextView.setText(infoUser.getName());
    }

    @Override
    public int getItemCount() {
        return infoUserList.size();
    }
}
