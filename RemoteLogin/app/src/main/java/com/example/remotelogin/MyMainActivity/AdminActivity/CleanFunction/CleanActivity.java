package com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.remotelogin.BaseActivity;
import com.example.remotelogin.R;
import com.google.android.material.tabs.TabLayout;

public class CleanActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CleanFragmentPagerAdapter fragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean);
        tabLayout = findViewById(R.id.clean_tabLayout);
        viewPager = findViewById(R.id.clean_viewPager);
        fragmentPagerAdapter = new CleanFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}