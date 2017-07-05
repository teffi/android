package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;
import static com.example.android.quakereport.EarthquakeActivity.LOG_TAG;

/**
 * Created by steffitan on 05/07/2017.
 */

public class EarthQuakeLoader extends AsyncTaskLoader<List<EarthQuake>> {
    //INNER CLASS EarthQuakeLoader
    String mRequestURL;
    public EarthQuakeLoader(Context context, String requestURL){
        super(context);
        Log.v(LOG_TAG,"Loader construct");
        mRequestURL = requestURL;
    }

    @Override
    public List<EarthQuake> loadInBackground() {
        Log.v(LOG_TAG,"Loader load in background");
        List<EarthQuake> quakes = QueryUtils.fetchEarthquakeData(mRequestURL);
        return quakes;
    }

    @Override
    protected void onStartLoading() {
        Log.v(LOG_TAG,"Loader start");
        forceLoad();
    }

}
