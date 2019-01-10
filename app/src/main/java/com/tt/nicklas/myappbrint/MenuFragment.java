package com.tt.nicklas.myappbrint;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MenuFragment extends Fragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("correct");

    private Button button;
    private Button shopBtn;
    private Button achiBtn;
    private  String dataBaseValue;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v =  inflater.inflate(R.layout.fragment_menu, container, false);
        button = (Button)v.findViewById(R.id.StartBtn);
        shopBtn = (Button)v.findViewById(R.id.shop);
        achiBtn = (Button)v.findViewById(R.id.achievements);

        button.setOnClickListener((start) -> {
            goToGame();
        });

        shopBtn.setOnClickListener((start) -> {
            goToShop();
        });
        achiBtn.setOnClickListener((achive) -> {
            goToAchivements();
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(myRef!= null){

                }
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                dataBaseValue = dataSnapshot.getValue(String.class);
                Log.d("tag","Value is: " + dataBaseValue);



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("test", "Failed to read value.", error.toException());
            }
        });

        return v;
    }

    public void goToGame(){
        getFragmentManager().beginTransaction().replace(R.id.is_fragment_container, new GameFragment()).addToBackStack(null).commit();


    }

    public void goToShop(){
        getFragmentManager().beginTransaction().replace(R.id.is_fragment_container, new ListOfWords()).addToBackStack(null).commit();


    }

    public void goToAchivements(){
        Bundle bundle = new Bundle();
        bundle.putString("editValue", dataBaseValue);
        AchievementsFragment achievementsFragment = new AchievementsFragment();
        achievementsFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.is_fragment_container, achievementsFragment).addToBackStack(null).commit();


    }






}
