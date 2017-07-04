package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by steffitan on 04/07/2017.
 */

public class CategoryPageAdapter extends FragmentPagerAdapter {

    //Constructor is required!
    public CategoryPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NumbersFragment();
            case 1:
                return new FamilyFragment();
            case 2:
                return new ColorsFragment();
            case 4:
                return new PhrasesFragment();
            default:
                return new NumbersFragment();
        }
    }


    @Override
    public int getCount() {
        return 4;
    }
}
