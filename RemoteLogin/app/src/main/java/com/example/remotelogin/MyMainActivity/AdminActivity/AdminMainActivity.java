package com.example.remotelogin.MyMainActivity.AdminActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.remotelogin.BaseActivity;
import com.example.remotelogin.ConstantValues;
import com.example.remotelogin.Login.AdminUser;
import com.example.remotelogin.R;

public class AdminMainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener {
    private TextView admin_txt_topbar;
    private RadioGroup admin_rg_tab_bar;
    private RadioButton admin_rb_channel;
    private RadioButton admin_rb_setting;
    private ViewPager admin_vpager;

    public AdminUser getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    private AdminUser adminUser;

    private AdminFragmentPageAdapter mAdapter;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        ConstantValues.maincontext = this;
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide(); //隐藏标题栏
        }
        Intent intent = getIntent();
        adminUser = (AdminUser) intent.getSerializableExtra("admin_user");
//        NormalUserInfoFragment normalUserInfoFragment = (NormalUserInfoFragment)
//                getSupportFragmentManager().findFragmentById(R.id.normalUserInfoFragment);
//        normalUserInfoFragment.setNormalUser(normalUser);
        mAdapter = new AdminFragmentPageAdapter(getSupportFragmentManager());
        bindViews();
        admin_rb_channel.setChecked(true);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // must store the new intent unless getIntent() will return the old one
        setIntent(intent);
    }

    private void bindViews() {
        admin_txt_topbar = (TextView) findViewById(R.id.admin_txt_topbar);
        admin_rg_tab_bar = (RadioGroup) findViewById(R.id.admin_rg_tab_bar);
        admin_rb_channel = (RadioButton) findViewById(R.id.admin_rb_channel);
        admin_rb_setting = (RadioButton) findViewById(R.id.admin_rb_setting);
        admin_rg_tab_bar.setOnCheckedChangeListener(this);

        admin_vpager = (ViewPager) findViewById(R.id.admin_vpager);
        admin_vpager.setAdapter(mAdapter);
        admin_vpager.setCurrentItem(0);
        admin_vpager.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.admin_rb_channel:
                admin_vpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.admin_rb_setting:
                admin_vpager.setCurrentItem(PAGE_TWO);
                break;
        }
    }


    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (admin_vpager.getCurrentItem()) {
                case PAGE_ONE:
                    admin_rb_channel.setChecked(true);
                    break;
                case PAGE_TWO:
                    admin_rb_setting.setChecked(true);
                    break;
            }
        }
    }
}
