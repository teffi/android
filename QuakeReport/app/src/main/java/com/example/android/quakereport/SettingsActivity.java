package com.example.android.quakereport;

import android.content.Loader;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class SettingsActivity extends AppCompatActivity {
    private static final String LOG_TAG = "Settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public static class EarthquakePreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(LOG_TAG,"Destroying Activity");
    }

    @Override
    protected void onResume() {
        super.onResume();

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
