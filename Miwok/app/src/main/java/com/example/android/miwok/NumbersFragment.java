package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
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

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener(){
        @Override
        public void onAudioFocusChange(int focusChange) {
            if  (focusChange == AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                //Resume playback
            }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    public NumbersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.words_list,container,false);

        mAudioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        ArrayList<Words> words = new ArrayList<Words>();
        words.add(new Words("one", "lutti", ResourceMapper.map(R.drawable.number_one, R.raw.number_one)));
        words.add(new Words("two", "otiiko", ResourceMapper.map(R.drawable.number_two, R.raw.number_two)));
        words.add(new Words("three", "tolookosu", ResourceMapper.map(R.drawable.number_three, R.raw.number_three)));
        words.add(new Words("four", "oyyisa", ResourceMapper.map(R.drawable.number_four, R.raw.number_four)));
        words.add(new Words("five", "massokka", ResourceMapper.map(R.drawable.number_five, R.raw.number_five)));
        words.add(new Words("six", "temmokka", ResourceMapper.map(R.drawable.number_six, R.raw.number_six)));
        words.add(new Words("seven", "kenekaku", ResourceMapper.map(R.drawable.number_seven, R.raw.number_seven)));
        words.add(new Words("eight", "kawinta", ResourceMapper.map(R.drawable.number_eight, R.raw.number_eight)));
        words.add(new Words("nine", "wo'e", ResourceMapper.map(R.drawable.number_nine, R.raw.number_nine)));
        words.add(new Words("ten", "na'aacha", ResourceMapper.map(R.drawable.number_ten, R.raw.number_ten)));


        WordsAdapter itemsAdapter = new WordsAdapter(getActivity(), words, R.color.category_numbers);

        final ListView listView = (ListView)rootView.findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Words word = (Words) parent.getItemAtPosition(position);
                releaseMediaPlayer();

                /*
                //Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        //Use the music stream
                        AudioManager.STREAM_MUSIC,
                        //Request permanent focus
                        AudioManager.AUDIOFOCUS_GAIN);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getMiwokAudio());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }*/

                mMediaPlayer = MediaPlayer.create(getActivity(), word.getMiwokAudio());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);

                Toast.makeText(getActivity(), "play", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
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

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

}
