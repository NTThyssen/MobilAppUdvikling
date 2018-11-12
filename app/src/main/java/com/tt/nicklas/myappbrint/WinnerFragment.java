package com.tt.nicklas.myappbrint;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class WinnerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        View view = inflater.inflate(R.layout.fragment_winner, container, false);
        Button tryAgain = (Button)view.findViewById(R.id.tryAgainWin);
        String theWordstr = bundle.getString("TheWord");

        TextView theWord = (TextView) view.findViewById(R.id.theWordTxt);
        theWord.setText(theWordstr);

        tryAgain.setOnClickListener((v)->{
            getFragmentManager().beginTransaction().replace(R.id.is_fragment_container, new GameFragment()).addToBackStack(null).commit();
        });
        return view;
    }

    }


