package com.thedevelopershome.taskbrochill.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.thedevelopershome.taskbrochill.fragment.HomeFragment;
import com.thedevelopershome.taskbrochill.fragment.ProfileFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "HOME", "DAVINDER SINGH"};

    private static int NUM_ITEMS = 2;
    Boolean enabled=false;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return new HomeFragment();
            case 1: // Fragment # 1 - This will show FirstFragment different title
                return new ProfileFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

}
