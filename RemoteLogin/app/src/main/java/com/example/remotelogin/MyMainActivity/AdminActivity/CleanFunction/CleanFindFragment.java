package com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanDataBaseOperation.FindAllCleanInfo;
import com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanDataBaseOperation.FindAllStaff;
import com.example.remotelogin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CleanFindFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CleanFindFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button findStaff;
    Button findForms;
    TextView staffInfo;
    TextView formsInfo;
    List<CleanStaffInfo> cleanStaffInfoList = new ArrayList<>();
    List<CleanForms> cleanFormsList = new ArrayList<>();

    public CleanFindFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CleanFindFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CleanFindFragment newInstance(String param1, String param2) {
        CleanFindFragment fragment = new CleanFindFragment();
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
        // todo 根据条件排序，刷新按钮
        View view = inflater.inflate(R.layout.fragment_clean_find, container, false);
        FindInfoViewModel viewModel = new ViewModelProvider(requireActivity()).get(FindInfoViewModel.class);
        viewModel.setContext(view.getContext());

        findForms = view.findViewById(R.id.findInfo_bt);
        findStaff = view.findViewById(R.id.findstaff_bt);
        formsInfo = view.findViewById(R.id.allforms_txt);
        staffInfo = view.findViewById(R.id.allstaff_txt);
        findForms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.FindForms();
            }
        });
        findStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.FindStaff();
            }
        });
        viewModel.getStaffResult().observe(requireActivity(), new Observer<List<CleanStaffInfo>>() {
            @Override
            public void onChanged(List<CleanStaffInfo> cleanStaffInfoList) {
                // todo 跟新textView
                String values = "here is the staff information.\n";
                for (int i = 0; i < cleanStaffInfoList.size(); i++) {
                    values += "id: " + cleanStaffInfoList.get(i).getId() + "\n";
                    values += "name: " + cleanStaffInfoList.get(i).getName() + "\n";
                    values += "hire_date: " + cleanStaffInfoList.get(i).getHireDate() + "\n";
                    values += "phone_number: " + cleanStaffInfoList.get(i).getPhoneNumber() + "\n";
                    values += "sex: " + cleanStaffInfoList.get(i).getSex() + "\n";
                    values += "\n";
                }
                staffInfo.setText(values);
            }
        });
        viewModel.getFormsResult().observe(requireActivity(), new Observer<List<CleanForms>>() {
            @Override
            public void onChanged(List<CleanForms> cleanFormsList) {
                String values = "here is the staff information.\n";
                for (int i = 0; i < cleanFormsList.size(); i++) {
                    values += "id: " + cleanFormsList.get(i).getId() + "\n";
                    values += "date: " + cleanFormsList.get(i).getCleanDate() + "\n";
                    values += "machine_id: " + cleanFormsList.get(i).getCleanMachineId() + "\n";
                    values += "clean_user_id: " + cleanFormsList.get(i).getCleanUserId() + "\n";
                    values += "\n";
                }
                formsInfo.setText(values);
            }
        });
        return view;
    }
}