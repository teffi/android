/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.LoaderManager;
import android.content.Loader;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<EarthQuake>> {


    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query";

    public static final String LOG_TAG = "Earthquake";

    TextView emptyTextView;
    ListView earthquakeListView;
    View overlayView;
    ProgressBar progressBar;
    EarthQuakeAdapter quakeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(LOG_TAG,"CREATING ACTIVITY");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        overlayView = (View)findViewById(R.id.overlay_view);

        emptyTextView = (TextView)findViewById(R.id.empty_text_view);
        // Find a reference to the {@link ListView} in the layout
        earthquakeListView = (ListView) findViewById(R.id.list);

        //Set listview empty view.
        earthquakeListView.setEmptyView(emptyTextView);

        progressBar = (ProgressBar)findViewById(R.id.progress_indicator);

        if(hasNetworkConnectivity()) {
            //1- id of loader,null -bundle, this - class with LoaderCallBacks
            //LoadManager - forceLoad() is executed in onResume block since loader must reload after getting the updated magnitude preference value from Settings Activity.
            getLoaderManager().initLoader(1, null, this);

        }
    }

    private Boolean hasNetworkConnectivity(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        //networkInfo is null if no network connection is available.lo
        if(networkInfo == null || networkInfo.isFailover()){
            progressBar.setVisibility(View.GONE);
            overlayView.setVisibility(View.GONE);
            overlayView.setAlpha(0);
            emptyTextView.setText("No Internet Connection");
            return false;
        }else{
            Log.v(LOG_TAG,"NETWORK INFO NULL");
        }
        return true;
    }


    private void updateUI(List<EarthQuake> quakes){
        if(quakeAdapter == null){

            Log.v(LOG_TAG,"INITIAL QUAKE ADAPTER CONFIG");
            quakeAdapter = new EarthQuakeAdapter(this,quakes);
            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            earthquakeListView.setAdapter(quakeAdapter);

            earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //Create intent for browser with quake's url
                    EarthQuake data = (EarthQuake)parent.getItemAtPosition(position);
                    String dataURL = data.getURL();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setData(Uri.parse(dataURL));
                    startActivity(browserIntent);
                }
            });


        }else{

            Log.v(LOG_TAG,"REUSE QUAKE ADAPTER - JUST UPDATES DATA");
            quakeAdapter.clear();
            quakeAdapter.addAll(quakes);
            quakeAdapter.notifyDataSetChanged();
        }
        Log.v(LOG_TAG,"Update UI");
    }

    //LOADER CALLBACKS
    @Override
    //onCreateLoader gets called only when there's no existing loader with the given id
    public Loader<List<EarthQuake>> onCreateLoader(int id, Bundle args) {
        Log.v(LOG_TAG,"Create loader");
        return new EarthQuakeLoader(EarthquakeActivity.this,getRequestURL());
    }

    @Override
    //Same with onPostExecute. Gets called when the task is finished
    public void onLoadFinished(Loader<List<EarthQuake>> loader, List<EarthQuake> data) {
        Log.v(LOG_TAG,"Loader finished");
        emptyTextView.setText("No earthquakes found");
        progressBar.setVisibility(View.GONE);
        overlayView.setVisibility(View.GONE);
        overlayView.setAlpha(0);
        if(data != null || !data.isEmpty()){
            updateUI(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<EarthQuake>> loader) {
        Log.v(LOG_TAG,"Loader Reset");
    }


    /* Implementation if using only AsyncTask w/o Loaders
    private class EarthQuakeAsync extends AsyncTask<String,Void,List<EarthQuake>>{
        @Override
        protected List<EarthQuake> doInBackground(String... params) {

            List<EarthQuake> quakes = QueryUtils.fetchEarthquakeData(params[0]);
            return quakes;
        }

        @Override
        protected void onPostExecute(List<EarthQuake> earthQuakes) {
            //super.onPostExecute(earthQuakes);
            updateUI(earthQuakes);

        }
    }*/

    private String getRequestURL(){
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String minMagnitude = sharedPrefs.getString(getString(R.string.settings_min_magnitude_key),getString(R.string.settings_min_magnitude_default));

        Log.v(LOG_TAG,"Min magnitude" + minMagnitude);

        Uri baseUri = Uri.parse(USGS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("format","geojson");
        uriBuilder.appendQueryParameter("limit", "10");
        uriBuilder.appendQueryParameter("minmag", minMagnitude);
        uriBuilder.appendQueryParameter("orderby", "time");
        return uriBuilder.toString();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(LOG_TAG,"Destroying Activity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //On resume is called when app is moving on top of the stack.

        //This only applies when app is reusing this activity - launchMode="singleTop"

        //Update loader request url to make sure that the magnitude in query is synced with what's in shared preferences
        Loader<List<EarthQuake>> loader = getLoaderManager().getLoader(1);
        EarthQuakeLoader quakeLoader = (EarthQuakeLoader)loader;
        //Update loader request url string.
        quakeLoader.setRequestURL(getRequestURL());

        progressBar.setVisibility(View.VISIBLE);
        overlayView.setVisibility(View.VISIBLE);
        overlayView.setAlpha((float) 0.8);

        //IMPT: Execute/Starts loader after updating quakeLoader url.
        loader.forceLoad();

        Log.v(LOG_TAG,"Resuming Activity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(LOG_TAG,"Pausing Activity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(LOG_TAG,"Stopping Activity");
    }
}
