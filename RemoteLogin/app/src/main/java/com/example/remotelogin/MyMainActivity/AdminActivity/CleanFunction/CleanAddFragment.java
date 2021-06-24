package com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanDataBaseOperation.AddCleanForm;
import com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanDataBaseOperation.AddCleanStaff;
import com.example.remotelogin.R;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CleanAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CleanAddFragment extends Fragment {

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
    private Button clean_staff_add_more_bt;
    private Button clean_staff_add_bt;
    private Button clean_info_add_more_bt;
    private Button clean_info_add;
    private View view;
    List<EditText> cleanStaffEdits;
    List<EditText> cleanInfoEdits;
    List<String> cleanInfoValues = new ArrayList<>();
    List<String> cleanStaffValues = new ArrayList<>();
    List<CleanStaffInfo> cleanStaffInfoList = new ArrayList<>();
    List<CleanForms> cleanFormsList = new ArrayList<>();

    public CleanAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CleanAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CleanAddFragment newInstance(String param1, String param2) {
        CleanAddFragment fragment = new CleanAddFragment();
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
        view = inflater.inflate(R.layout.fragment_clean_add, container, false);
        initViews();
        clean_staff_add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 建立实例，传入，并插入到数据库
                if (!cleanStaffInfoList.isEmpty()) {
                    new AddCleanStaff(v.getContext()).execute(cleanStaffInfoList);
                } else {
                    Toast.makeText(v.getContext(), "请点击add_more添加数据", Toast.LENGTH_LONG).show();
                }
            }
        });
        clean_staff_add_more_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取实例，清空
                getStaffList(v);
            }
        });
        clean_info_add_more_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfoList(v);
            }
        });
        clean_info_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cleanFormsList.isEmpty()) {
                    new AddCleanForm(v.getContext()).execute(cleanFormsList);
                } else {
                    Toast.makeText(v.getContext(), "请点击add_more添加数据", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    private void getInfoList(View v) {
        for (int i = 0; i < cleanInfoEdits.size(); i++) {
            String value = cleanInfoEdits.get(i).getText().toString();
            if (value.isEmpty()) {
                Toast.makeText(v.getContext(), "输入不能为空", Toast.LENGTH_LONG).show();
                return;
            }
            cleanInfoValues.add(value);
        }
        for (int i = 0; i < cleanInfoEdits.size(); i++) {
            cleanInfoEdits.get(i).setText("");
        }
        CleanForms cleanForms = createCleanInfo(cleanInfoValues);
        cleanFormsList.add(cleanForms);
    }

    private CleanForms createCleanInfo(List<String> cleanInfoValues) {
        CleanForms cleanForms = new CleanForms();
        cleanForms.setId(Integer.parseInt(cleanInfoValues.get(0)));
        cleanForms.setCleanDate(Date.valueOf(cleanInfoValues.get(1)));
        cleanForms.setCleanMachineId(Integer.parseInt(cleanInfoValues.get(2)));
        cleanForms.setCleanUserId(Integer.parseInt(cleanInfoValues.get(3)));
        return cleanForms;
    }

    private void getStaffList(View v) {
        for (int i = 0; i < cleanStaffEdits.size(); i++) {
            String value = cleanStaffEdits.get(i).getText().toString();
            if (value.isEmpty()) {
                Toast.makeText(v.getContext(), "输入不能为空", Toast.LENGTH_LONG).show();
                return;
            }
            cleanStaffValues.add(value);
        }
        // 清空
        for (int i = 0; i < cleanStaffEdits.size(); i++) {
            cleanStaffEdits.get(i).setText("");
        }
        CleanStaffInfo cleanStaffInfo = createCleanStaff(cleanStaffValues);
        cleanStaffInfoList.add(cleanStaffInfo);
    }

    private CleanStaffInfo createCleanStaff(List<String> cleanStaffValues) {
        CleanStaffInfo cleanStaffInfo = new CleanStaffInfo();
        cleanStaffInfo.setId(Integer.parseInt(cleanStaffValues.get(0)));
        cleanStaffInfo.setName(cleanStaffValues.get(1));
        cleanStaffInfo.setHireDate(Date.valueOf(cleanStaffValues.get(2)));
        cleanStaffInfo.setPhoneNumber(cleanStaffValues.get(3));
        cleanStaffInfo.setSex(cleanStaffValues.get(4));
        return cleanStaffInfo;
    }

    private void initViews() {
        cleanStaffEdits = new ArrayList<>();
        cleanInfoEdits = new ArrayList<>();

        clean_info_id = view.findViewById(R.id.clean_info_id);
        clean_info_date = view.findViewById(R.id.clean_info_clean_date);
        clean_info_machine_id = view.findViewById(R.id.clean_info_machine_id_txt);
        clean_info_user_id = view.findViewById(R.id.clean_info_user_id_txt);
        cleanInfoEdits.add(clean_info_id);
        cleanInfoEdits.add(clean_info_date);
        cleanInfoEdits.add(clean_info_machine_id);
        cleanInfoEdits.add(clean_info_user_id);

        clean_staff_id = view.findViewById(R.id.clean_staff_info_id);
        clean_staff_name = view.findViewById(R.id.clean_staff_name);
        clean_staff_hire_date = view.findViewById(R.id.clean_staff_hire_date);
        clean_staff_ph = view.findViewById(R.id.clean_staff_ph);
        clean_staff_sex = view.findViewById(R.id.clean_staff_sex);
        cleanStaffEdits.add(clean_staff_id);
        cleanStaffEdits.add(clean_staff_name);
        cleanStaffEdits.add(clean_staff_hire_date);
        cleanStaffEdits.add(clean_staff_ph);
        cleanStaffEdits.add(clean_staff_sex);

        clean_staff_add_more_bt = view.findViewById(R.id.add_more_clean_staff_bt);
        clean_staff_add_bt = view.findViewById(R.id.clean_staff_bt);
        clean_info_add_more_bt = view.findViewById(R.id.clean_info_add_more_bt);
        clean_info_add = view.findViewById(R.id.clean_form_bt);
    }
}