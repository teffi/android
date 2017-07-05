package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by steffitan on 04/07/2017.
 */

public class EarthQuakeAdapter extends ArrayAdapter<EarthQuake> {

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
        TextView dateTextView = (TextView)itemView.findViewById(R.id.date_text_view);
        TextView timeTextView= (TextView)itemView.findViewById(R.id.time_text_view);

        EarthQuake model = getItem(position);
        magnitudeTextView.setText(model.getMagnitude());
        locationTextView.setText(model.getLocation());
        dateTextView.setText(model.getDate());
        timeTextView.setText(model.getTime());
        return itemView;
    }

}
