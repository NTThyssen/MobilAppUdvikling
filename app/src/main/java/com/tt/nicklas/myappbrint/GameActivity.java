package com.tt.nicklas.myappbrint;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    private Drawable drawable;
    private ContextThemeWrapper  themeWapper;
    private ImageView imageView;
    private TextView textView;
    private EditText userInput;
    private TextView wrongText;
    GameLogic gameObject = new GameLogic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Button b = (Button) findViewById(R.id.GuessBtn);
        themeWapper = new ContextThemeWrapper(this, R.style.default1);
        drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_hangman, themeWapper.getTheme());
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageDrawable(drawable);
        textView = (TextView)findViewById(R.id.word);
        wrongText = (TextView)findViewById(R.id.wrongText);


        textView.setText(gameObject.getVisibleWord());

        b.setOnClickListener((userGuess) -> {
            userInput = (EditText)findViewById(R.id.userInput);
            gameObject.guessLetter(userInput.getText().toString());
            textView.setText(gameObject.getVisibleWord());
            if(!gameObject.wasLastLetterCorrect()){
                wrongText.setText(wrongText.getText()+userInput.getText().toString());
                updateHangman();
            }


            userInput.getText().clear();
        });


    }


    public void updateHangman(){
        Resources.Theme theme = getResources().newTheme();
        switch (gameObject.getWrongGuesses()){
            case 1:
                theme.applyStyle(R.style.fail1, true);
                changeTheme(theme);
                break;
            case 2:
                theme.applyStyle(R.style.fail2, true);
                changeTheme(theme);
                break;
            case 3:
                theme.applyStyle(R.style.fail3, true);
                changeTheme(theme);
                break;
            case 4:
                theme.applyStyle(R.style.fail4, true);
                changeTheme(theme);
            case 5:
                theme.applyStyle(R.style.fail5, true);
                changeTheme(theme);
                break;
            case 6:
                theme.applyStyle(R.style.fail6, true);
                changeTheme(theme);
                gameObject.isGameIsLost();

        }
    }



    public void changeTheme(Resources.Theme theme) {
        final Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_hangman, theme);
        imageView.setImageDrawable(drawable);
    }




}
