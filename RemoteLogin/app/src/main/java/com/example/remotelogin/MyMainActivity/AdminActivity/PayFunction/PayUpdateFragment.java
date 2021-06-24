package com.example.remotelogin.MyMainActivity.AdminActivity.PayFunction;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanDataBaseOperation.ChangeCleanForm;
import com.example.remotelogin.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PayUpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PayUpdateFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private EditText cost_id_edit;
    private EditText user_id_edit;
    private EditText date_edit;
    private EditText water_edit;
    private EditText elect_edit;
    private EditText done_edit;
    private EditText should_edit;
    private EditText payed_edit;
    private EditText unpay_edit;
    private Button update_bt;

    public PayUpdateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PayUpdateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PayUpdateFragment newInstance(String param1, String param2) {
        PayUpdateFragment fragment = new PayUpdateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pay_update, container, false);
        initViews();
        update_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = cost_id_edit.getText().toString();
                if (id.isEmpty()) {
                    Toast.makeText(v.getContext(), "post_cost_id不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                String sql = "update post_cost set ";
                Boolean something = false;
                String value = user_id_edit.getText().toString();
                if (!value.isEmpty()) {
                    sql += "post_user_id=" + value + ",";
                    something = true;
                }
                value = date_edit.getText().toString();
                if (!value.isEmpty()) {
                    sql += "date='" + value + "',";
                    something = true;
                }
                value = water_edit.getText().toString();
                if (!value.isEmpty()) {
                    sql += "water_cost=" + value + ", ";
                    something = true;
                }
                value = elect_edit.getText().toString();
                if (!value.isEmpty()) {
                    sql += "elect_cost=" + value + ", ";
                    something = true;
                }
                value = done_edit.getText().toString();
                if (!value.isEmpty()) {
                    sql += "done=" + value + ", ";
                    something = true;
                }
                value = should_edit.getText().toString();
                if (!value.isEmpty()) {
                    sql += "should_pay=" + value + ", ";
                    something = true;
                }
                value = payed_edit.getText().toString();
                if (!value.isEmpty()) {
                    sql += "payed=" + value + ", ";
                    something = true;
                }
                value = unpay_edit.getText().toString();
                if (!value.isEmpty()) {
                    sql += "unpay=" + value + " ";
                    something = true;
                }
                if (!something) {
                    Toast.makeText(v.getContext(), "输入要更改的内容", Toast.LENGTH_LONG).show();
                    return;
                }
                sql += "where id=" + id;
                new ChangeCleanForm(v.getContext()).execute(sql);
            }
        });
        return view;
    }

    private void initViews() {
        cost_id_edit = view.findViewById(R.id.admin_update_post_cost_id_edit);
        user_id_edit = view.findViewById(R.id.admin_update_post_user_id_edit);
        date_edit = view.findViewById(R.id.admin_update_date_edit);
        water_edit = view.findViewById(R.id.admin_update_water_edit);
        elect_edit = view.findViewById(R.id.admin_update_elect_edit);
        done_edit = view.findViewById(R.id.admin_update_done_edit);
        should_edit = view.findViewById(R.id.admin_update_should_edit);
        payed_edit = view.findViewById(R.id.admin_update_payed_edit);
        unpay_edit = view.findViewById(R.id.admin_update_unpay_edit);
        update_bt = view.findViewById(R.id.admin_pay_update_bt);
    }

}