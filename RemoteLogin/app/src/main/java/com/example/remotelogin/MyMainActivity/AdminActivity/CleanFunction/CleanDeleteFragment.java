package com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanDataBaseOperation.IDDeleteCleanInfo;
import com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanDataBaseOperation.IDDeleteStaff;
import com.example.remotelogin.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CleanDeleteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CleanDeleteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button staffDel;
    Button formDel;
    EditText staffID;
    EditText formID;

    public CleanDeleteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CleanDeleteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CleanDeleteFragment newInstance(String param1, String param2) {
        CleanDeleteFragment fragment = new CleanDeleteFragment();
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
        View view = inflater.inflate(R.layout.fragment_clean_delete, container, false);
        staffDel = view.findViewById(R.id.staff_delete_bt);
        formDel = view.findViewById(R.id.form_delete_bt);
        staffID = view.findViewById(R.id.delete_staff_id);
        formID = view.findViewById(R.id.delete_form_id);
        staffDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = staffID.getText().toString();
                if (value.isEmpty()) {
                    Toast.makeText(v.getContext(), "ID不能为空", Toast.LENGTH_LONG).show();
                } else {
                    new IDDeleteStaff(v.getContext()).execute(value);
                }
            }
        });
        formDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = formID.getText().toString();
                if (value.isEmpty()) {
                    Toast.makeText(v.getContext(), "ID不能为空", Toast.LENGTH_LONG).show();
                } else {
                    new IDDeleteCleanInfo(v.getContext()).execute(value);
                }
            }
        });
        return view;
    }
}