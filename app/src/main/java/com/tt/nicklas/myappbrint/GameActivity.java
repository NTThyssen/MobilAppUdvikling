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
    private int fails = -1;
    Button b;
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
            System.out.println(gameObject.isGameIsLost());
                userInput = (EditText)findViewById(R.id.userInput);
                gameObject.guessLetter(userInput.getText().toString());
                textView.setText(gameObject.getVisibleWord());

                if(!gameObject.wasLastLetterCorrect()){
                    wrongText.setText(wrongText.getText()+" "+userInput.getText().toString());
                    updateHangman(gameObject.getWrongGuesses());
                    b.setEnabled(gameState());

                }
                userInput.getText().clear();
        });


    }

    public boolean gameState(){
        if(gameObject.isGameIsLost()){
            textView.setText("YOU LOST!");
            return false;
        }
        if(gameObject.isGameIsWon()){
            textView.setText("YOU WON!");
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
