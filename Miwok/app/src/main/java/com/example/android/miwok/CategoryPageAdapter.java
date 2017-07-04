package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by steffitan on 04/07/2017.
 */

public class CategoryPageAdapter extends FragmentPagerAdapter {

    //Context of the app
    private Context mContext;

    //Constructor is required!
    public CategoryPageAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
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
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.category_numbers);
            case 1:
                return mContext.getString(R.string.category_family);
            case 2:
                return mContext.getString(R.string.category_colors);
            case 4:
                return mContext.getString(R.string.category_phrases);
            default:
                return mContext.getString(R.string.category_numbers);
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
