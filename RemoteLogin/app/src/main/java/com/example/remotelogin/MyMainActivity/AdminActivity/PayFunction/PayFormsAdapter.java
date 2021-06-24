package com.example.remotelogin.MyMainActivity.AdminActivity.PayFunction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remotelogin.MyMainActivity.AdminActivity.PayFunction.PayDatabaseOperation.PayForms;
import com.example.remotelogin.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PayFormsAdapter extends RecyclerView.Adapter<PayFormsAdapter.ViewHolder> {
    private List<PayForms> payFormsList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView postCostTextView;
        TextView postUserTextView;
        TextView postDateTextView;
        TextView shouldPayTextView;
        TextView payedTextView;
        TextView electCostTextView;
        TextView waterCostTextView;
        TextView doneTextView;

        public ViewHolder(View view) {
            super(view);
            postCostTextView = view.findViewById(R.id.admin_pay_post_cost_id);
            postUserTextView = view.findViewById(R.id.admin_pay_post_user_id);
            postDateTextView = view.findViewById(R.id.admin_pay_post_date);
            shouldPayTextView = view.findViewById(R.id.admin_pay_should_pay);
            payedTextView = view.findViewById(R.id.admin_pay_payed);
            electCostTextView = view.findViewById(R.id.admin_pay_elect_cost);
            waterCostTextView = view.findViewById(R.id.admin_pay_water_cost);
            doneTextView = view.findViewById(R.id.admin_pay_done_txt);
        }
    }

    public PayFormsAdapter(List<PayForms> payFormsList) {
        this.payFormsList = payFormsList;
    }

    @NonNull
    @NotNull
    @Override
    public PayFormsAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pay_form_item, parent, false);
        PayFormsAdapter.ViewHolder holder = new PayFormsAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        PayForms payForms = payFormsList.get(position);
        holder.postCostTextView.setText(payForms.getPostCostId());
        holder.postUserTextView.setText(payForms.getPostUserId());
        holder.doneTextView.setText(String.valueOf(payForms.getDone()));
        holder.electCostTextView.setText(String.valueOf(payForms.getElectCost()));
        holder.waterCostTextView.setText(String.valueOf(payForms.getWaterCost()));
        holder.postDateTextView.setText(String.valueOf(payForms.getDate()));
        holder.shouldPayTextView.setText(String.valueOf(payForms.getShouldPay()));
        holder.payedTextView.setText(String.valueOf(payForms.getPayed()));
    }

    @Override
    public int getItemCount() {
        return payFormsList.size();
    }
}