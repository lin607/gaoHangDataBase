package com.example.remotelogin.MyMainActivity.AdminActivity;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class AdminFragmentPageAdapter extends FragmentPagerAdapter {
    private final int PAGER_COUNT = 2;
    private AdminUserInfoFragment infoFragment = null;
    private AdminUserOperateFragment operateFragment = null;


    public AdminFragmentPageAdapter(FragmentManager fm) {
        super(fm);
        operateFragment = new AdminUserOperateFragment();
        infoFragment = new AdminUserInfoFragment();
    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        System.out.println("position Destory" + position);
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case AdminMainActivity.PAGE_ONE:
                fragment = operateFragment;
                break;
            case AdminMainActivity.PAGE_TWO:
                fragment = infoFragment;
                break;
        }
        return fragment;
    }
}

