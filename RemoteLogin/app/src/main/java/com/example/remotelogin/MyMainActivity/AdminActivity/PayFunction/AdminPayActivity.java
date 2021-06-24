package com.example.remotelogin.MyMainActivity.AdminActivity.PayFunction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.remotelogin.R;
import com.google.android.material.tabs.TabLayout;

public class AdminPayActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AdminPayFragmentPagerAdapter fragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pay);
        tabLayout = findViewById(R.id.admin_pay_tabLayout);
        viewPager = findViewById(R.id.admin_pay_viewPager);
        fragmentPagerAdapter = new AdminPayFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}