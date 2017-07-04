package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.DrawableRes;
import android.support.annotation.RawRes;
import android.support.v4.app.NavUtils;
import android.support.v4.util.SimpleArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;

public class NumbersActivity extends AppCompatActivity {

    //https://classroom.udacity.com/courses/ud839/lessons/7876183922/concepts/81537585740923
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set activity_category.xml as the activity contentview.
        //activity_category serves as a container which will be replaced by gthe fragment layout.
        setContentView(R.layout.activity_category);
        //Present numberfragment which has the word_list xml layout.
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new NumbersFragment()).commit();
    }


    //Back button event handler
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            //Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
