package com.tt.nicklas.myappbrint;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoserFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoserFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        View view = inflater.inflate(R.layout.fragment_loser, container, false);
        Button tryAgain = (Button)view.findViewById(R.id.tryAgain);
        String theWordtoGuess = bundle.getString("TheWordToGuess");

        TextView theWord = (TextView) view.findViewById(R.id.theWordTxt);
        theWord.setText(theWordtoGuess);

        tryAgain.setOnClickListener((v)->{
            getFragmentManager().beginTransaction().replace(R.id.is_fragment_container, new GameFragment()).addToBackStack(null).commit();
        });
        return view;
    }

}
