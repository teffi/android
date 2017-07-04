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
public class PhrasesFragment extends Fragment {
    MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.words_list,container,false);

        ArrayList<Words> words = new ArrayList<Words>();
        words.add(new Words("Where are you going?","minto wuksus",ResourceMapper.map(-1,R.raw.phrase_where_are_you_going)));
        words.add(new Words("What is your name?","tinnә oyaase'nә",ResourceMapper.map(-1,R.raw.phrase_what_is_your_name)));
        words.add(new Words("My name is...","oyaaset...",ResourceMapper.map(-1,R.raw.phrase_my_name_is)));
        words.add(new Words("How are you feeling?","michәksәs?",ResourceMapper.map(-1,R.raw.phrase_how_are_you_feeling)));
        words.add(new Words("I’m feeling good.","kuchi achit",ResourceMapper.map(-1,R.raw.phrase_im_feeling_good)));
        words.add(new Words("Are you coming?","әәnәs'aa?",ResourceMapper.map(-1,R.raw.phrase_are_you_coming)));
        words.add(new Words("Yes, I’m coming.","hәә’ әәnәm",ResourceMapper.map(-1,R.raw.phrase_yes_im_coming)));
        words.add(new Words("I’m coming.","әәnәm",ResourceMapper.map(-1,R.raw.phrase_im_coming)));
        words.add(new Words("Let’s go.","yoowutis",ResourceMapper.map(-1,R.raw.phrase_lets_go)));
        words.add(new Words("Come here.","әnni'nem",ResourceMapper.map(-1,R.raw.phrase_come_here)));

        WordsAdapter itemsAdapter = new WordsAdapter(getActivity(),words,R.color.category_phrases);

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
