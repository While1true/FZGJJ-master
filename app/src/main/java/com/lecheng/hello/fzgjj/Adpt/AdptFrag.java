package com.lecheng.hello.fzgjj.Adpt;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Cheng on 2015/8/6.
 */
public class AdptFrag extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public AdptFrag(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}