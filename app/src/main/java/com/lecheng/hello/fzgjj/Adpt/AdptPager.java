package com.lecheng.hello.fzgjj.Adpt;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lecheng.hello.fzgjj.Activity.H1.NewsPager;

public class AdptPager extends FragmentPagerAdapter {

    private Context context;
//    private static final String[] mTitles = {"工作动态", "政策法规", "办事指南", "政务公开"};
    private static final String[] mTitles = {"通知公告","工作动态", "政策法规"};

    public AdptPager(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return NewsPager.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}