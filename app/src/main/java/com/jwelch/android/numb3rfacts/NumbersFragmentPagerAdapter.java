package com.jwelch.android.numb3rfacts;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by jwelch on 6/30/15.
 */
public class NumbersFragmentPagerAdapter extends FragmentPagerAdapter {

    // will be 4
    final int PAGE_COUNT = 1;
    private String[] mPageTitles = new String[] {
            "Math Facts",
    };

    public NumbersFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MathFragment.newInstance();
            default:
                return MathFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPageTitles[position];
    }
}
