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


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<EarthQuake>> {

    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=5&limit=10";

    public static final String LOG_TAG = "Earthquake";

    TextView emptyTextView;
    ListView earthquakeListView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        emptyTextView = (TextView)findViewById(R.id.empty_text_view);
        // Find a reference to the {@link ListView} in the layout
        earthquakeListView = (ListView) findViewById(R.id.list);

        //Set listview empty view.
        earthquakeListView.setEmptyView(emptyTextView);

        progressBar = (ProgressBar)findViewById(R.id.progress_indicator);

        //1- id of loader,null -bundle, this - class with LoaderCallBacks
        getLoaderManager().initLoader(1,null,this);
    }

    private void updateUI(List<EarthQuake> quakes){
        EarthQuakeAdapter quakeAdapter = new EarthQuakeAdapter(this,quakes);

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

        Log.v(LOG_TAG,"Update UI");
    }

    //LOADER CALLBACKS
    @Override
    //onCreateLoader gets called only when there's no existing loader with the given id
    public Loader<List<EarthQuake>> onCreateLoader(int id, Bundle args) {
        Log.v(LOG_TAG,"Create loader");
        return new EarthQuakeLoader(EarthquakeActivity.this,USGS_REQUEST_URL);
    }

    @Override
    //Same with onPostExecute. Gets called when the task is finished
    public void onLoadFinished(Loader<List<EarthQuake>> loader, List<EarthQuake> data) {
        Log.v(LOG_TAG,"Loader finished");
        emptyTextView.setText("No earthquakes found");
        progressBar.setVisibility(View.GONE);
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



}
