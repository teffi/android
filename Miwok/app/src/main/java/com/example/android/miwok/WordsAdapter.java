package com.example.android.miwok;


import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by steffitan on 23/06/2017.
 */

//REFERENCE: https://github.com/udacity/ud839_CustomAdapter_Example/blob/master/app/src/main/java/com/example/android/flavor/AndroidFlavorAdapter.java

public class WordsAdapter extends ArrayAdapter<Words> {

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param Words A List of Words objects to display in a list
     */
    int layoutID;
    int color;


    public WordsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Words> objects) {
        super(context, resource, objects);
        layoutID = resource;
    }

    public WordsAdapter(@NonNull Context context, @NonNull ArrayList<Words> objects, @ColorRes int colorResource) {
        super(context, 0, objects);
        layoutID = R.layout.single_list_item;
        color = colorResource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View itemView = convertView;
        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(layoutID,parent,false);
        }
        // Get the Words object located at this position in the list
        Words currentWord = getItem(position);

        // Find the TextView objects from the layout xml.
        TextView defaultTextView = (TextView)itemView.findViewById(R.id.default_text_view);
        TextView miwokTextView = (TextView)itemView.findViewById(R.id.miwok_text_view);
        ViewGroup viewGroup = (ViewGroup)itemView.findViewById(R.id.textview_group);
        ImageView imageView = (ImageView)itemView.findViewById(R.id.list_image);

        if(currentWord.getImage() != -1){
            imageView.setImageResource(currentWord.getImage());
            imageView.setVisibility(View.VISIBLE);
        }else{
            imageView.setVisibility(View.GONE);
        }

        //Set TextView text from Words object properties
        defaultTextView.setText(currentWord.getDefaultTranslation());
        miwokTextView.setText(currentWord.getMiwokTranslation());

        //Format color
        viewGroup.setBackgroundResource(color);

        return itemView;
    }
}
