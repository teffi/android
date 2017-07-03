package com.example.android.miwok;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by steffitan on 22/06/2017.
 */

public class NumbersClickListener implements View.OnClickListener {
    @Override
    public void onClick(View view){

        //getResourceEntryName return id name in string

        //Determine the view that triggers the event
        switch (view.getId()){
            case R.id.numbers:
                Toast.makeText(view.getContext(),"Open the list of "+ view.getResources().getResourceEntryName(view.getId()),Toast.LENGTH_SHORT).show();
                break;
            case R.id.phrases:
                Toast.makeText(view.getContext(),"List of "+ view.getResources().getResourceEntryName(view.getId()),Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }
}
