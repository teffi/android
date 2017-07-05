package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by steffitan on 04/07/2017.
 */

public class EarthQuake {
    Double mMagnitude;
    long mDateInMilliseconds;
    String mLocation;
    String mURL;

    Date mDate;

    public EarthQuake(String location, long date, Double magnitude, String url){
        mMagnitude = magnitude;
        mLocation = location;
        mDateInMilliseconds = date;
        mURL = url;
    }

    public static EarthQuake create(String location, long date, Double magnitude,String url){
        return new EarthQuake(location,date,magnitude,url);
    }

    public String getMagnitude(){
        DecimalFormat formatter = new DecimalFormat("0.0");

        return formatter.format(mMagnitude);
    }

    public String getURL(){
        return mURL;
    }

    public Double getMagnitudeInDouble(){
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
