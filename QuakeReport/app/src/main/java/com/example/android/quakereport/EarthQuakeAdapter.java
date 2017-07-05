package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by steffitan on 04/07/2017.
 */

public class EarthQuakeAdapter extends ArrayAdapter<EarthQuake> {

    private static final String LOCATION_SEPARATOR = " of ";

    public EarthQuakeAdapter(Context context, @NonNull ArrayList<EarthQuake> objects){
        super(context,R.layout.quake_list_item,objects);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View itemView = convertView;

        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.quake_list_item,parent,false);
        }
        TextView magnitudeTextView = (TextView)itemView.findViewById(R.id.magnitude_text_view);
        TextView locationTextView= (TextView)itemView.findViewById(R.id.location_text_view);
        TextView geoTextView= (TextView)itemView.findViewById(R.id.geo_text_view);
        TextView dateTextView = (TextView)itemView.findViewById(R.id.date_text_view);
        TextView timeTextView= (TextView)itemView.findViewById(R.id.time_text_view);

        EarthQuake model = getItem(position);
        magnitudeTextView.setText(model.getMagnitude());

        dateTextView.setText(model.getDate());
        timeTextView.setText(model.getTime());

        if(model.getLocation().indexOf(LOCATION_SEPARATOR) != -1){
            String locations[] = model.getLocation().split(LOCATION_SEPARATOR);
            geoTextView.setText(locations[0] + LOCATION_SEPARATOR);
            locationTextView.setText(locations[1]);
        }else{
            geoTextView.setText("Near the");
            locationTextView.setText(model.getLocation());
        }


        //Changing magnitude drawable shape background color
        //Access drawable color
        GradientDrawable magnitudeDrawable = (GradientDrawable) magnitudeTextView.getBackground();
        //Convert color resource to Color
        magnitudeDrawable.setColor(getMagnitudeColor(model.getMagnitudeInDouble()));
        return itemView;
    }

    private int getMagnitudeColor(double magnitude){
        @ColorRes int magnitudeBG;
        //Floor - finds the closest integer less than the decimal value.
        int magnitudeFloor = (int)Math.floor(magnitude);

        switch (magnitudeFloor){
            case 0:
            case 1:
                magnitudeBG = R.color.magnitude1;
                break;
            case 2:
                magnitudeBG = R.color.magnitude2;
                break;
            case 3:
                magnitudeBG = R.color.magnitude3;
                break;
            case 4:
                magnitudeBG = R.color.magnitude4;
                break;
            case 5:
                magnitudeBG = R.color.magnitude5;
                break;
            case 6:
                magnitudeBG = R.color.magnitude6;
                break;
            case 7:
                magnitudeBG = R.color.magnitude7;
                break;
            case 8:
                magnitudeBG = R.color.magnitude8;
                break;
            case 9:
                magnitudeBG = R.color.magnitude9;
                break;

            default:
                magnitudeBG = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(),magnitudeBG);
    }

}
