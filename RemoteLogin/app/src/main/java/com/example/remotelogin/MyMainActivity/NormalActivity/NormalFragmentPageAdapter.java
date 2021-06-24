package com.example.remotelogin.MyMainActivity.NormalActivity;

import android.graphics.Path;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class NormalFragmentPageAdapter extends FragmentPagerAdapter {
    private final int PAGER_COUNT = 2;
    private NormalUserOperateFragment operateFragment = null;
    private NormalUserInfoFragment infoFragment = null;


    public NormalFragmentPageAdapter(FragmentManager fm) {
        super(fm);
        operateFragment = new NormalUserOperateFragment();
        infoFragment = new NormalUserInfoFragment();
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
            case NormalUserMainActivity.PAGE_ONE:
                fragment = operateFragment;
                break;
            case NormalUserMainActivity.PAGE_TWO:
                fragment = infoFragment;
                break;
        }
        return fragment;
    }
}
