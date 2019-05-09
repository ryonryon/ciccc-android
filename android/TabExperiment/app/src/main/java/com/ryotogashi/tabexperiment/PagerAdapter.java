package com.ryotogashi.tabexperiment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        if (i == 0) {
            return new TabFragment1();
        } else if (i == 1) {
            return new TabFragment2();
        } else if (i == 2) {
            return new TabFragment3();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
