package com.example.android.miwok;

import android.content.Context;
import android.media.Image;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.RawRes;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import static com.example.android.miwok.ResourceMapper.AUDIO_RESOURCE;
import static com.example.android.miwok.ResourceMapper.IMAGE_RESOURCE;

/**
 * Created by steffitan on 23/06/2017.
 */
public class Words {


    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mMiwokAudio;

    @Override //Shortcut CMD+n
    public String toString() {
        return "Words{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mMiwokAudio=" + mMiwokAudio +
                ", mImageResourceID=" + mImageResourceID +
                '}';
    }

    private int mImageResourceID;
    public Words(String english, String miwok, int imageID, @RawRes int songName){
        mDefaultTranslation = english;
        mMiwokTranslation = miwok;
        mImageResourceID = imageID;
        mMiwokAudio = songName;
    }

    /*public Words(String english, String miwok,@RawRes int songName){
        mDefaultTranslation = english;
        mMiwokTranslation = miwok;
        mMiwokAudio = songName;
        mImageResourceID = -1;
    }*/

    public Words(String english, String miwok, SimpleArrayMap<String,Integer> resources){
        mDefaultTranslation = english;
        mMiwokTranslation = miwok;
        mImageResourceID = resources.get(IMAGE_RESOURCE);
        mMiwokAudio = resources.get(AUDIO_RESOURCE);
    }

    public Words(String english, String miwok, @DrawableRes int imageID){
        mDefaultTranslation = english;
        mMiwokTranslation = miwok;
        mImageResourceID = imageID;
    }

    public Words(String english, String miwok){
        mDefaultTranslation = english;
        mMiwokTranslation = miwok;
        mImageResourceID = -1;
    }

    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }

    public @RawRes int getMiwokAudio(){
        return mMiwokAudio;
    }

    public int getImage(){
        return mImageResourceID;
    }
}
