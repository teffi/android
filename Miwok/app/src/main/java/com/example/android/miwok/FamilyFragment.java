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
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {
    MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.words_list,container,false);

        ArrayList<Words> words = new ArrayList<Words>();

        words.add(new Words("father","әpә",ResourceMapper.map(R.drawable.family_father,R.raw.family_father)));
        words.add(new Words("mother","әṭa",ResourceMapper.map(R.drawable.family_mother,R.raw.family_mother)));
        words.add(new Words("son","angsi",ResourceMapper.map(R.drawable.family_son,R.raw.family_son)));
        words.add(new Words("daughter","tune",ResourceMapper.map(R.drawable.family_daughter,R.raw.family_daughter)));
        words.add(new Words("older brother","taachi",ResourceMapper.map(R.drawable.family_older_brother,R.raw.family_older_brother)));
        words.add(new Words("younger brother","chalitti",ResourceMapper.map(R.drawable.family_younger_brother,R.raw.family_younger_brother)));
        words.add(new Words("older sister","teṭe",ResourceMapper.map(R.drawable.family_older_sister,R.raw.family_older_sister)));
        words.add(new Words("younger sister","kolliti",ResourceMapper.map(R.drawable.family_younger_sister,R.raw.family_younger_sister)));
        words.add(new Words("grandmother","ama",ResourceMapper.map(R.drawable.family_grandmother,R.raw.family_grandmother)));
        words.add(new Words("grandfather","paap",ResourceMapper.map(R.drawable.family_grandfather,R.raw.family_grandfather)));

        WordsAdapter itemsAdapter = new WordsAdapter(getActivity(),words,R.color.category_family);
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
                Toast.makeText(getActivity(), "play", Toast.LENGTH_SHORT).show();
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
