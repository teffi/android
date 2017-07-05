package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by steffitan on 04/07/2017.
 */

public class EarthQuake {
    String mMagnitude;
    long mDateInMilliseconds;
    String mLocation;
    Date mDate;

    public EarthQuake(String location, long date, String magnitude){
        mMagnitude = magnitude;
        mLocation = location;
        mDateInMilliseconds = date;
    }

    public static EarthQuake create(String location, long date, String magnitude){
        return new EarthQuake(location,date,magnitude);
    }

    public String getMagnitude(){
        return mMagnitude;
    }

    private Date getmDate(){
        return new Date(mDateInMilliseconds);
    }

    public String getDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM DD, yyyy");
        String dateInString = dateFormat.format(getmDate());
        return dateInString;
    }

    public String getTime(){
        SimpleDateFormat timeFormat= new SimpleDateFormat("h:mm a");
        return timeFormat.format(getmDate());
    }

    public String getLocation(){
        return mLocation;
    }

    public long getDateInMilliseconds(){
        return mDateInMilliseconds;
    }

}
