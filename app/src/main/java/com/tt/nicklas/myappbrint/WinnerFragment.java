package com.tt.nicklas.myappbrint;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.github.jinatonic.confetti.CommonConfetti;
import com.github.jinatonic.confetti.ConfettiView;

import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;


public class WinnerFragment extends Fragment {


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // ConfettiView confettiView;
        final MediaPlayer mp = MediaPlayer.create(getContext(), R.raw.winsound);
        mp.start();
        Bundle bundle = getArguments();

        View view = inflater.inflate(R.layout.fragment_winner, container, false);
        CommonConfetti.rainingConfetti(container, new int[] { Color.BLACK })
                .infinite();

        Button tryAgain = (Button)view.findViewById(R.id.tryAgainWin);
        String theWordstr = bundle.getString("TheWord");

        TextView theWord = (TextView) view.findViewById(R.id.theWordTxt);
        theWord.setText(theWordstr);

        tryAgain.setOnClickListener((v)->{
            getFragmentManager().beginTransaction().replace(R.id.is_fragment_container, new GameFragment()).commit();
        });
        return view;
    }


    public void showConfetti() {

    }
    }



