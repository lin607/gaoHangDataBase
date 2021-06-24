package com.example.remotelogin.MyMainActivity.AdminActivity.PayFunction;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanDataBaseOperation.AddCleanForm;
import com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanForms;
import com.example.remotelogin.MyMainActivity.AdminActivity.PayFunction.PayDatabaseOperation.AddPayForms;
import com.example.remotelogin.MyMainActivity.AdminActivity.PayFunction.PayDatabaseOperation.PayForms;
import com.example.remotelogin.R;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PayAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PayAddFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText cost_id_edit;
    private EditText user_id_edit;
    private EditText date_edit;
    private EditText water_edit;
    private EditText elect_edit;
    private EditText done_edit;
    private EditText should_edit;
    private EditText payed_edit;
    private EditText unpay_edit;
    private Button add_more_bt;
    private Button add_bt;
    private List<EditText> editTextList;
    private List<PayForms> payFormsList = new ArrayList<>();
    private View view;

    public PayAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PayAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PayAddFragment newInstance(String param1, String param2) {
        PayAddFragment fragment = new PayAddFragment();
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
        view = inflater.inflate(R.layout.fragment_pay_add, container, false);
        initViews();
        add_more_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInputFormsList(v);
            }
        });
        add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!payFormsList.isEmpty()) {
                    new AddPayForms(v.getContext()).execute(payFormsList);
                } else {
                    Toast.makeText(v.getContext(), "请点击add_more添加数据", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    private void getInputFormsList(View v) {
        List<String> values = new ArrayList<>();
        for (int i = 0; i < editTextList.size(); i++) {
            String value = editTextList.get(i).getText().toString();
            if (value.isEmpty()) {
                Toast.makeText(v.getContext(), "输入不能为空", Toast.LENGTH_LONG).show();
                return;
            }
            values.add(value);
        }
        for (int i = 0; i < editTextList.size(); i++) {
            editTextList.get(i).setText("");
        }
        PayForms payForms = createPayForm(values);
        payFormsList.add(payForms);
    }

    private PayForms createPayForm(List<String> values) {
        PayForms payForms = new PayForms();
        payForms.setPostCostId(Integer.parseInt(values.get(0)));
        payForms.setPostUserId(Integer.parseInt(values.get(1)));
        payForms.setDate(Date.valueOf(values.get(2)));
        payForms.setWaterCost(Double.parseDouble(values.get(3)));
        payForms.setElectCost(Double.parseDouble(values.get(4)));
        payForms.setDone(Boolean.parseBoolean(values.get(5)));
        payForms.setShouldPay(Double.parseDouble(values.get(6)));
        payForms.setPayed(Double.parseDouble(values.get(7)));
        payForms.setUnpay(Double.parseDouble(values.get(8)));
        return payForms;
    }

    private void initViews() {
        editTextList = new ArrayList<>();
        cost_id_edit = view.findViewById(R.id.admin_add_post_cost_id_edit);
        user_id_edit = view.findViewById(R.id.admin_add_post_user_id_edit);
        date_edit = view.findViewById(R.id.admin_add_date_edit);
        water_edit = view.findViewById(R.id.admin_add_water_edit);
        elect_edit = view.findViewById(R.id.admin_add_elect_edit);
        done_edit = view.findViewById(R.id.admin_add_done_edit);
        should_edit = view.findViewById(R.id.admin_add_should_edit);
        payed_edit = view.findViewById(R.id.admin_add_payed_edit);
        unpay_edit = view.findViewById(R.id.admin_add_unpay_edit);
        editTextList.add(cost_id_edit);
        editTextList.add(user_id_edit);
        editTextList.add(date_edit);
        editTextList.add(water_edit);
        editTextList.add(elect_edit);
        editTextList.add(done_edit);
        editTextList.add(should_edit);
        editTextList.add(payed_edit);
        editTextList.add(unpay_edit);
        add_more_bt = view.findViewById(R.id.admin_pay_add_more_bt);
        add_bt = view.findViewById(R.id.admin_pay_add_bt);
    }
}