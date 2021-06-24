package com.example.remotelogin.MyMainActivity.AdminActivity.CleanFunction;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CleanFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"add", "find", "update", "delete"};
    private List<Fragment> fragments = new ArrayList<>();

    public CleanFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        fragments.add(CleanAddFragment.newInstance("1","1"));
        fragments.add(CleanFindFragment.newInstance("2","2"));
        fragments.add(CleanUpdateFragment.newInstance("3","3"));
        fragments.add(CleanDeleteFragment.newInstance("4","4"));
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
