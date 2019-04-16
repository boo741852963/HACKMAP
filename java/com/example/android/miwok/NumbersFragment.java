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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {
    private MediaPlayer mdp;
    private AudioManager adm;
    private MediaPlayer.OnCompletionListener mcl = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };
    private AudioManager.OnAudioFocusChangeListener AFCL = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if(i==AudioManager.AUDIOFOCUS_LOSS)
            {
                mdp.stop();
                releaseMediaPlayer();
            }
            else if(i==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT)
                mdp.pause();
            else if(i==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
                mdp.pause();
            else {
                mdp.start();
                mdp.seekTo(0);
            }
        }};

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_numbers, container, false);
        adm = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<words> wordss = new ArrayList<words>();
        wordss.add(new words("One","Aaak",R.drawable.number_one,R.raw.nnr1));
        wordss.add(new words("Two","Dui",R.drawable.number_two,R.raw.nnr2));
        wordss.add(new words("Three","Teen",R.drawable.number_three,R.raw.nnr3));
        wordss.add(new words("Four","Char",R.drawable.number_four,R.raw.nnr4));
        wordss.add(new words("Five","Paach",R.drawable.number_five,R.raw.nnr5));
        wordss.add(new words("Six","Chhoe",R.drawable.number_six,R.raw.nnr6));
        wordss.add(new words("Seven","Shat",R.drawable.number_seven,R.raw.nnr7));
        wordss.add(new words("Eight","Aat",R.drawable.number_eight,R.raw.nnr8));
        wordss.add(new words("Nine","Noye",R.drawable.number_nine,R.raw.nnr9));
        wordss.add(new words("Ten","Dosh",R.drawable.number_ten,R.raw.nnr10));
        WordAdapterr Adapter = new WordAdapterr(getActivity(),wordss,R.color.category_numbers);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(Adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                words wd=wordss.get(i);
                releaseMediaPlayer();
                int result=adm.requestAudioFocus(AFCL,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback.
                    mdp = MediaPlayer.create(getActivity(), wd.getAudresid());
                    mdp.start();
                    mdp.setOnCompletionListener(mcl);
                }
            }
        });
        return rootView;
    }
    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mdp != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mdp.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mdp = null;
            adm.abandonAudioFocus(AFCL);
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
