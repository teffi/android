package com.example.android.quakereport;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.prefs.PreferenceChangeListener;

public class SettingsActivity extends AppCompatActivity {
    private static final String LOG_TAG = "Settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public static class EarthquakePreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);

            Preference minMagnitude = findPreference(getString(R.string.settings_min_magnitude_key));
            bindPreferenceSummaryToValue(minMagnitude);

        }

        /*
        To do this, our PreferenceFragment can implement the OnPreferenceChangeListener interface to get notified when a preference changes.
        Then when a single Preference has been changed by the user and is about to be saved, the onPreferenceChange() method
        will be invoked with the key of the preference that was changed.
        Note that this method returns a boolean, which allows us to prevent a proposed preference change by returning false.
        */

        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();
            //Sets the string to appear below Minimum Magnitude
            preference.setSummary(stringValue);
            return true;
        }

        private void bindPreferenceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
            String preferenceString = preferences.getString(preference.getKey(), "");
            onPreferenceChange(preference, preferenceString);
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
