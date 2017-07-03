package com.example.android.miwok;

import android.support.annotation.DrawableRes;
import android.support.annotation.RawRes;
import android.support.v4.util.SimpleArrayMap;


/**
 * Created by steffitan on 30/06/2017.
 */

public abstract class ResourceMapper {
    public static final String IMAGE_RESOURCE = "image";
    public static final String AUDIO_RESOURCE= "audio";

    public static SimpleArrayMap<String,Integer> map(@DrawableRes int image, @RawRes int audio){
        SimpleArrayMap<String,Integer> map = new SimpleArrayMap();
        map.put(IMAGE_RESOURCE,image);
        map.put(AUDIO_RESOURCE,audio);
        return map;
    }
}
