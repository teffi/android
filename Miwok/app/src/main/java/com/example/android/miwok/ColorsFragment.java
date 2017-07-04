package com.example.android.miwok;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {
    MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.words_list,container,false);

        ArrayList<Words> words = new ArrayList<Words>();
        words.add(new Words("red","weṭeṭṭi",ResourceMapper.map(R.drawable.color_red,R.raw.color_red)));
        words.add(new Words("green","chokokki",ResourceMapper.map(R.drawable.color_green,R.raw.color_green)));
        words.add(new Words("brown","ṭakaakki",ResourceMapper.map(R.drawable.color_brown,R.raw.color_brown)));
        words.add(new Words("gray","ṭopoppi",ResourceMapper.map(R.drawable.color_gray,R.raw.color_gray)));
        words.add(new Words("black","kululli",ResourceMapper.map(R.drawable.color_black,R.raw.color_black)));
        words.add(new Words("white","kelelli",ResourceMapper.map(R.drawable.color_white,R.raw.color_white)));
        words.add(new Words("dusty yellow","ṭopiisә",ResourceMapper.map(R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow)));
        words.add(new Words("mustard yellow","chiwiiṭә",ResourceMapper.map(R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow)));

        WordsAdapter itemsAdapter = new WordsAdapter(getActivity(),words,R.color.category_colors);

        ListView listView = (ListView)rootView.findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Words word = (Words) parent.getItemAtPosition(position);
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(getActivity(), word.getMiwokAudio());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
        return rootView;
    }


    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }

}
