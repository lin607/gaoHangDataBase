package com.example.remotelogin.MyMainActivity.AdminActivity.PayFunction;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.remotelogin.MyMainActivity.AdminActivity.PayFunction.PayDatabaseOperation.PayForms;
import com.example.remotelogin.R;

import org.xml.sax.helpers.ParserAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PaySearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaySearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private PayFormsAdapter payFormsAdapter;
    private PaySearchResultViewModel viewModel;

    public PaySearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PaySearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PaySearchFragment newInstance(String param1, String param2) {
        PaySearchFragment fragment = new PaySearchFragment();
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
        View view = inflater.inflate(R.layout.fragment_pay_search, container, false);
        Button refresh_bt = view.findViewById(R.id.admin_pay_search_refresh_bt);
        RecyclerView recyclerView = view.findViewById(R.id.admin_pay_search_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        viewModel = new ViewModelProvider(requireActivity()).get(PaySearchResultViewModel.class);
        viewModel.setContext(view.getContext());
        viewModel.getPayFormViewModel();
        viewModel.getPayFormsMutableLiveData().observe(requireActivity(), new Observer<List<PayForms>>() {
            @Override
            public void onChanged(List<PayForms> payForms) {
                payFormsAdapter = new PayFormsAdapter(payForms);
                recyclerView.setAdapter(payFormsAdapter);
            }
        });
        refresh_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPayFormViewModel();
            }
        });
        return view;
    }
}