package com.tt.nicklas.myappbrint;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ThemesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ThemesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThemesFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getFragmentManager().beginTransaction().replace(R.id.is_fragment_container, new GameFragment()).addToBackStack(null).commit();

        return inflater.inflate(R.layout.fragment_themes, container, false);
    }

}