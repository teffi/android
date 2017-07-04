package com.example.android.miwok;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;

import static com.example.android.miwok.MainActivity.imageResId;
import static com.example.android.miwok.MainActivity.tabTitles;


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
        //ICON ONLY - Return no title. Tab icon is set on activity that holds the tab layout – MainActivity
        return null;

        /* PLAIN TITLE
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
        }*/


        /*
        //Add Icons+Text to TabLayout
        //IMPT: use ContextCompat getDrawable. Requires ApplicationContext and not the Activity Context
        //IMPT: Tab "textAllCaps" should be FALSE for this to work.

        Drawable image = ContextCompat.getDrawable(mContext.getApplicationContext(), imageResId[position]);
        image.setBounds(0,0,image.getIntrinsicWidth(),image.getIntrinsicHeight());

        //Replace blank spaces with image icon
        SpannableString sb = new SpannableString(" " + mContext.getString(tabTitles[position]));
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BASELINE);
        sb.setSpan(image,0,1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;

        */
    }
    @Override
    public int getCount() {
        return 4;
    }


}
