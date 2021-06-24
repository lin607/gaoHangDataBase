package com.example.remotelogin.MyMainActivity.NormalActivity.PayCashFunction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.remotelogin.BaseActivity;
import com.example.remotelogin.Login.FindUserViewModel;
import com.example.remotelogin.R;

import java.util.ArrayList;
import java.util.List;

public class NormalUserPayActivity extends BaseActivity {

    Button check_pay_record_bt;
    Button check_pay_cash_bt;
    Button pay_cost_bt;
    TextView show_content_txt;
    Double pay_cash;
    String id;
    EditText user_input_edit_view;
    List<PostCostElement> postCostElements;
    NormalUserPayViewModel normalUserPayViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user_pay);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        postCostElements = new ArrayList<>();
        check_pay_record_bt = findViewById(R.id.check_pay_record_bt);
        check_pay_cash_bt = findViewById(R.id.check_pay_cash_bt);
        pay_cost_bt = findViewById(R.id.pay_cost_bt);
        show_content_txt = findViewById(R.id.show_pay_content_txt);
        user_input_edit_view = findViewById(R.id.pay_cash_eidt_text);
        normalUserPayViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(NormalUserPayViewModel.class);
        check_pay_cash_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normalUserPayViewModel.findPayCash(id);
                // todo 根据id获取数据库数据
                // todo 设置textView展示金额
            }
        });
        normalUserPayViewModel.viewModelGetPayCash().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                show_content_txt.setText("You should pay " + aDouble + "yuan.");
            }
        });
        check_pay_record_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  todo 根据id查询记录
                normalUserPayViewModel.findRecord(id);
                // TODO 设置textView(RecycleView)展示数据
            }
        });
        normalUserPayViewModel.getListMutableLiveData().observe(this, new Observer<List<PostCostElement>>() {
            @Override
            public void onChanged(List<PostCostElement> postCostElements) {
                if (postCostElements.isEmpty()) {
                    show_content_txt.setText("no record!");
                } else {
                    for (int i = 0; i < postCostElements.size(); i++) {
                        StringBuilder value = new StringBuilder();
                        value.append(String.valueOf(postCostElements.get(i).getPostCostId()));
                        value.append("\n");
                        value.append(String.valueOf(postCostElements.get(i).getPostUserId()));
                        value.append("\n");
                        value.append(postCostElements.get(i).getDate().toString());
                        value.append("\n");
                        value.append(String.valueOf(postCostElements.get(i).getWaterCost()));
                        value.append("\n");
                        value.append(String.valueOf(postCostElements.get(i).getElectCost()));
                        value.append("\n");
                        value.append(String.valueOf(postCostElements.get(i).getDone()));
                        value.append("\n");
                        value.append(String.valueOf(postCostElements.get(i).getShouldPay()));
                        value.append("\n");
                        value.append(String.valueOf(postCostElements.get(i).getPayed()));
                        value.append("\n");
                        value.append(String.valueOf(postCostElements.get(i).getUnpay()));
                        value.append("\n");
                        value.append("\n");
                        show_content_txt.setText(value);
                    }
                }
            }
        });
        pay_cost_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo 修改post_id
                String post_id = "1";
                String cash = user_input_edit_view.getText().toString();
                if (cash.isEmpty()) {
                    Toast.makeText(v.getContext(), "金额不能为空", Toast.LENGTH_LONG).show();
                } else if (Double.parseDouble(cash) < 0) {
                    Toast.makeText(v.getContext(), "输入正确的金额", Toast.LENGTH_LONG).show();
                } else {
                    normalUserPayViewModel.findChangePayCash(post_id, cash);
                }
                // TODO 根据id修改金额，应该有获取金额的操作
                // todo 展示当月还剩的应缴金额
            }
        });
    }
}