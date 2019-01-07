package com.tt.nicklas.myappbrint;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class ThemesFragment extends Fragment {


    ImageView imageViewTheme1;
    ImageView imageViewTheme2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_themes, container, false);
        imageViewTheme1 = view.findViewById(R.id.imageView3);
        imageViewTheme2 = view.findViewById(R.id.imageView4);
        Button choseThemeBtn1 = view.findViewById(R.id.choseTheme);

        choseThemeBtn1.setOnClickListener((view1)->{
            System.out.println("wtf???!?!-----------------");
            Bundle chose = new Bundle();
            chose.putString("theme", "default" );
            MenuFragment menuFragment = new MenuFragment();
            menuFragment.setArguments(chose);
            getFragmentManager().beginTransaction().replace(R.id.is_fragment_container, new MenuFragment()).commit();
        });
        Button choseThemeBtn2 = view.findViewById(R.id.choseTheme2);


        choseThemeBtn2.setOnClickListener((view1)->{
            System.out.println("wtf???!?!---------------");
            Bundle chose = new Bundle();
            chose.putString("theme", "theme1" );
            MenuFragment menuFragment = new MenuFragment();
            menuFragment.setArguments(chose);
            getFragmentManager().beginTransaction().replace(R.id.is_fragment_container, new MenuFragment()).commit();
        });

        return inflater.inflate(R.layout.fragment_themes, container, false);
    }



}