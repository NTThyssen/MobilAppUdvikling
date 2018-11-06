package com.tt.nicklas.myappbrint;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;


public class MenuFragment extends Fragment {


    private Button button;
    private Button shopBtn;
    public MenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_menu, container, false);
        button = (Button)v.findViewById(R.id.StartBtn);
        shopBtn = (Button)v.findViewById(R.id.shop);
        button.setOnClickListener((start) -> {
            goToGame();
        });

        shopBtn.setOnClickListener((start) -> {

        });
        return v;
    }

    public void goToGame(){

        Fragment gameFragment = new GameFragment();
        FragmentTransaction fm = getFragmentManager().beginTransaction();
        fm.add(R.id.is_fragment_container, new GameFragment()).commit();


    }





}
