package com.example.remotelogin.MyMainActivity.AdminActivity.PayFunction;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.remotelogin.MyMainActivity.AdminActivity.PayFunction.PayDatabaseOperation.DeletePayForms;
import com.example.remotelogin.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PayDeleteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PayDeleteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText del_id_edit;
    private Button del_bt;

    public PayDeleteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PayDeleteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PayDeleteFragment newInstance(String param1, String param2) {
        PayDeleteFragment fragment = new PayDeleteFragment();
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
        View view = inflater.inflate(R.layout.fragment_pay_delete, container, false);
        del_id_edit = view.findViewById(R.id.admin_pay_delete_id);
        del_bt = view.findViewById(R.id.admin_pay_delete_bt);
        del_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = del_id_edit.getText().toString();
                if (value.isEmpty()) {
                    Toast.makeText(v.getContext(), "id不能为空", Toast.LENGTH_LONG).show();
                } else {
                    new DeletePayForms(v.getContext()).execute(value);
                }
            }
        });
        return view;
    }
}