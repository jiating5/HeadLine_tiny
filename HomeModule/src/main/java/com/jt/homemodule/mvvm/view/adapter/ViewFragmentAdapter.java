package com.mvp.financemodule.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.List;

public class ViewFragmentAdapter extends FragmentPagerAdapter {
    private List<String> tabs;
    private List<Fragment> list;

    public ViewFragmentAdapter(FragmentManager fm, List<String> tabs, List<Fragment> list) {
        super(fm);
        this.tabs = tabs;
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        return list != null ? list.size():0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position);
    }
}
