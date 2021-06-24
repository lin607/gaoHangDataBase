package com.example.remotelogin.MyMainActivity.NormalActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.remotelogin.Login.NormalUser;
import com.example.remotelogin.MyMainActivity.NormalActivity.BoardCommentFunction.NormalUserBoardMainActivity;
import com.example.remotelogin.MyMainActivity.NormalActivity.PayCashFunction.NormalUserPayActivity;
import com.example.remotelogin.MyMainActivity.NormalActivity.ReportDeviceRepairFunction.NormalUserDeviceRepairActivity;
import com.example.remotelogin.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NormalUserOperateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NormalUserOperateFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private NormalUser normalUser;
    private Button pay_bt;
    private Button repair_bt;
    private Button board_bt;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NormalUserOperateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NormalUserOperateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NormalUserOperateFragment newInstance(String param1, String param2) {
        NormalUserOperateFragment fragment = new NormalUserOperateFragment();
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
        View view = inflater.inflate(R.layout.fragment_normal_user_operate, container, false);
        NormalUserMainActivity activity = (NormalUserMainActivity) getActivity();
        normalUser = activity.getNormalUser();
        pay_bt = view.findViewById(R.id.post_pay_bt);
        repair_bt = view.findViewById(R.id.normal_user_device_bt);
        board_bt = view.findViewById(R.id.normal_user_board_bt);
        pay_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NormalUserPayActivity.class);
                intent.putExtra("id", normalUser.getId().toString());
                startActivity(intent);
            }
        });
        repair_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NormalUserDeviceRepairActivity.class);
                intent.putExtra("id", normalUser.getId());
                startActivity(intent);
            }
        });
        board_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NormalUserBoardMainActivity.class);
                intent.putExtra("id", normalUser.getId());
                intent.putExtra("name", normalUser.getName());
                startActivity(intent);
            }
        });
        return view;
    }
}