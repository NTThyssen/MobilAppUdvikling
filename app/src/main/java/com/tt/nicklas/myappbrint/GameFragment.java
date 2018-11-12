package com.tt.nicklas.myappbrint;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class GameFragment extends Fragment {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("correct");
    private Drawable drawable;
    private ContextThemeWrapper themeWapper;
    private ImageView imageView;
    private TextView textView;
    private EditText userInput;
    private TextView wrongText;
    private Context context;
    private boolean wordsFromDrIsDownloaded= false;
    GameLogic gameObject = new GameLogic();
    public GameFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        userInput = (EditText) view.findViewById(R.id.userInput);
        Button b = (Button) view.findViewById(R.id.GuessBtn);
        themeWapper = new ContextThemeWrapper(getActivity(), R.style.default1);
        drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_hangman, themeWapper.getTheme());
        imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageDrawable(drawable);
        textView = (TextView) view.findViewById(R.id.word);
        wrongText = (TextView) view.findViewById(R.id.wrongText);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        b.setEnabled(false);
        userInput.setEnabled(false);



            textView.setText("Henter ord fra DRs server....");
            new AsyncTask() {
                @Override
                protected Object doInBackground(Object... arg0) {
                    try {
                        gameObject.retrieveWordsFromDr();

                        return "";
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "Ordene blev ikke hentet korrekt: " + e;
                    }
                }

                @Override
                protected void onPostExecute(Object resultat) {
                    progressBar.setVisibility(View.INVISIBLE);
                    textView.setText(gameObject.getVisibleWord());
                    b.setEnabled(true);
                    userInput.setEnabled(true);
                    System.out.println(gameObject.getWordToGuess()+"-------------------------------------------");
                }
            }.execute();



        b.setOnClickListener((userGuess) -> {
            gameObject.guessLetter(userInput.getText().toString());
            textView.setText(gameObject.getVisibleWord());

            if (!gameObject.wasLastLetterCorrect()) {
                wrongText.setText(wrongText.getText() + " " + userInput.getText().toString());
                updateHangman(gameObject.getWrongGuesses());
                b.setEnabled(gameState());

            }

            userInput.getText().clear();

            //increments the users correct guessed words
            if(gameObject.isGameIsWon()) {

                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        myRef.setValue(Integer.toString(Integer.parseInt(value)+1));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Bundle bundle = new Bundle();
                bundle.putString("TheWord", "Du Vandt!\nAntal Fejl : "+gameObject.getWrongGuesses());
                WinnerFragment winnerFragment = new WinnerFragment();
                winnerFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.is_fragment_container, winnerFragment).addToBackStack(null).commit();
            }
        });



        return view;
    }

    public boolean gameState(){
        if(gameObject.isGameIsLost()){
            textView.setText("YOU LOST!");
            textView.setText(gameObject.getWordToGuess());
            Bundle bundle = new Bundle();
            bundle.putString("TheWordToGuess", gameObject.getWordToGuess());
            LoserFragment loserFragment = new LoserFragment();
            loserFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.is_fragment_container, loserFragment).addToBackStack(null).commit();
            return false;
        }

        return true;
    }


    public void updateHangman(int i){
        int[] hangState = {R.style.fail1, R.style.fail2, R.style.fail3, R.style.fail4, R.style.fail5, R.style.fail6};
        Resources.Theme theme = getResources().newTheme();
        theme.applyStyle(hangState[i-1], true);
        changeTheme(theme);

    }





    public void changeTheme(Resources.Theme theme) {
        final Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_hangman, theme);
        imageView.setImageDrawable(drawable);
    }


}
