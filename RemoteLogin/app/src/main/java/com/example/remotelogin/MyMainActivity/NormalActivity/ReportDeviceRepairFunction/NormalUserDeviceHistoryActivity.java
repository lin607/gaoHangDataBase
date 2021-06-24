package com.example.remotelogin.MyMainActivity.NormalActivity.ReportDeviceRepairFunction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.remotelogin.BaseActivity;
import com.example.remotelogin.R;

import java.util.List;

public class NormalUserDeviceHistoryActivity extends BaseActivity {
    EditText mark;
    RatingBar star;
    Button okk;
    String mark_value = "0";
    int id;
    NormalUserReportAdapter reportAdapter;
    RecyclerView recyclerView;
    NormalUserDeviceViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user_device_history);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);

        mark = (EditText) this.findViewById(R.id.editText2);
        mark.setInputType(InputType.TYPE_NULL);
        star = (RatingBar) this.findViewById(R.id.star);
        okk = (Button) this.findViewById(R.id.OK);

        recyclerView = (RecyclerView) this.findViewById(R.id.history_recyc);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        reportAdapter = new NormalUserReportAdapter(mark, NormalUserDeviceHistoryActivity.this);
        recyclerView.setAdapter(reportAdapter);

        userViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(NormalUserDeviceViewModel.class);
        userViewModel.Report_info(String.valueOf(id));

        userViewModel.getAllreports().observe(NormalUserDeviceHistoryActivity.this, new Observer<List<NormalUserReport>>() {
            @Override
            public void onChanged(List<NormalUserReport> reports) {
                reportAdapter.setReports(reports);
                reportAdapter.notifyDataSetChanged();
            }
        });

        star.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mark_value = String.valueOf(rating);
                Toast.makeText(NormalUserDeviceHistoryActivity.this, mark_value, Toast.LENGTH_SHORT).show();
            }
        });

        okk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String r_id = mark.getText().toString();
                if (!r_id.isEmpty()) {
                    userViewModel.Mark_repair(r_id, mark_value);
                    userViewModel.Report_info(String.valueOf(id));
                    mark.setText("");
                    Toast.makeText(NormalUserDeviceHistoryActivity.this, "Scored successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NormalUserDeviceHistoryActivity.this, "The report id cannot be null!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}