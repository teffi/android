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
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=5&limit=10";
    
    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    ListView earthquakeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Find a reference to the {@link ListView} in the layout
        earthquakeListView = (ListView) findViewById(R.id.list);

        EarthQuakeAsync task = new EarthQuakeAsync();
        task.execute(USGS_REQUEST_URL);
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
    }
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
    }
}
