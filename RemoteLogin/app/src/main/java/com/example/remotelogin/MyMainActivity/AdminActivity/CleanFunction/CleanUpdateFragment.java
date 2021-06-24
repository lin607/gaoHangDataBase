package com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanDataBaseOperation.ChangeCleanForm;
import com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanDataBaseOperation.ChangeCleanStaff;
import com.example.remotelogin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CleanUpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CleanUpdateFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText clean_info_id;
    private EditText clean_info_date;
    private EditText clean_info_machine_id;
    private EditText clean_info_user_id;
    private EditText clean_staff_id;
    private EditText clean_staff_name;
    private EditText clean_staff_hire_date;
    private EditText clean_staff_ph;
    private EditText clean_staff_sex;
    private Button clean_staff_update_bt;
    private Button clean_info_update_bt;
    private View view;

    public CleanUpdateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CleanUpdateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CleanUpdateFragment newInstance(String param1, String param2) {
        CleanUpdateFragment fragment = new CleanUpdateFragment();
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
        view = inflater.inflate(R.layout.fragment_clean_update, container, false);
        initViews();
        clean_staff_update_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = clean_staff_id.getText().toString();
                if (id.isEmpty()) {
                    Toast.makeText(v.getContext(), "id不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                String sql = "update info_clean_staff set ";
                Boolean something = false;
                String value = clean_staff_name.getText().toString();
                if (!value.isEmpty()) {
                    sql += "name='" + value + "',";
                    something = true;
                }
                value = clean_staff_hire_date.getText().toString();
                if (!value.isEmpty()) {
                    sql += "hire_date='" + value + "',";
                    something = true;
                }
                value = clean_staff_ph.getText().toString();
                if (!value.isEmpty()) {
                    sql += "phone_number='" + value + "',";
                    something = true;
                }
                value = clean_staff_sex.getText().toString();
                if (!value.isEmpty()) {
                    sql += "sex='" + value + "' ";
                    something = true;
                }
                if (!something) {
                    Toast.makeText(v.getContext(), "输入要更改的内容", Toast.LENGTH_LONG).show();
                }
                sql += "where id=" + id;
                new ChangeCleanStaff(v.getContext()).execute(sql);
            }
        });
        clean_info_update_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = clean_info_id.getText().toString();
                if (id.isEmpty()) {
                    Toast.makeText(v.getContext(), "id不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                String sql = "update clean_info set ";
                Boolean something = false;
                String value = clean_info_date.getText().toString();
                if (!value.isEmpty()) {
                    sql += "date='" + value + "',";
                    something = true;
                }
                value = clean_info_machine_id.getText().toString();
                if (!value.isEmpty()) {
                    sql += "machine_id=" + value + ",";
                    something = true;
                }
                value = clean_info_user_id.getText().toString();
                if (!value.isEmpty()) {
                    sql += "user_id=" + value + " ";
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
        clean_info_id = view.findViewById(R.id.update_clean_info_id);
        clean_info_date = view.findViewById(R.id.update_clean_info_clean_date);
        clean_info_machine_id = view.findViewById(R.id.update_clean_info_machine_id_txt);
        clean_info_user_id = view.findViewById(R.id.update_clean_info_user_id_txt);

        clean_staff_id = view.findViewById(R.id.update_clean_staff_info_id);
        clean_staff_name = view.findViewById(R.id.update_clean_staff_name);
        clean_staff_hire_date = view.findViewById(R.id.update_clean_staff_hire_date);
        clean_staff_ph = view.findViewById(R.id.update_clean_staff_ph);
        clean_staff_sex = view.findViewById(R.id.update_clean_staff_sex);

        clean_staff_update_bt = view.findViewById(R.id.update_clean_staff_bt);
        clean_info_update_bt = view.findViewById(R.id.update_clean_form_bt);
    }
}