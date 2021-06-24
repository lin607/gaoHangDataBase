package com.example.remotelogin.MyMainActivity.AdminActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.remotelogin.Login.AdminUser;
import com.example.remotelogin.MyMainActivity.AdminActivity.BoardFunction.AdminUserBoardMainActivity;
import com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction.CleanActivity;
import com.example.remotelogin.MyMainActivity.AdminActivity.DeviceFunction.AdminUserDeviceActivity;
import com.example.remotelogin.MyMainActivity.AdminActivity.GetInfo.AdminShowUserInfoActivity;
import com.example.remotelogin.MyMainActivity.AdminActivity.PayFunction.AdminPayActivity;
import com.example.remotelogin.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminUserOperateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminUserOperateFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    Button checkButton;
    AdminUser adminUser;
    Button payButton;
    Button cleanButton;
    Button deviceButton;
    Button boardButton;
    private String mParam1;
    private String mParam2;

    public AdminUserOperateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminUserOperateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminUserOperateFragment newInstance(String param1, String param2) {
        AdminUserOperateFragment fragment = new AdminUserOperateFragment();
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
        View view = inflater.inflate(R.layout.fragment_admin_user_operate, container, false);
        checkButton = view.findViewById(R.id.admin_user_check_bt);
        payButton = view.findViewById(R.id.admin_post_pay_bt);
        cleanButton = view.findViewById(R.id.admin_clean_bt);
        deviceButton = view.findViewById(R.id.admin_user_device_bt);
        boardButton = view.findViewById(R.id.admin_user_board_bt);
        AdminMainActivity activity = (AdminMainActivity) getActivity();
        adminUser = activity.getAdminUser();
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), AdminShowUserInfoActivity.class);
                startActivity(intent);
            }
        });
        cleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), CleanActivity.class);
                startActivity(intent);
            }
        });
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), AdminPayActivity.class);
                startActivity(intent);
            }
        });
        deviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), AdminUserDeviceActivity.class);
                startActivity(intent);
            }
        });
        boardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), AdminUserBoardMainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}