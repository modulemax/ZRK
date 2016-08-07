package com.zhengrenkun.zrk.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by RK on 2016/7/31.
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<Fragment> mFragments;
    public MainFragmentAdapter(FragmentManager fm, List<Fragment> fragments, Context context) {
        super(fm);
        mContext=context;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return 4;
    }
}
