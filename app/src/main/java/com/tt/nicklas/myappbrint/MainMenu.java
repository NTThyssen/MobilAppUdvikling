package com.tt.nicklas.myappbrint;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.StyleRes;
import android.support.annotation.StyleableRes;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;




public class MainMenu extends AppCompatActivity {
    int wrongAnswer = 0;
    Drawable drawable;
    ContextThemeWrapper  themeWapper;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Button b = (Button) findViewById(R.id.button);
        themeWapper = new ContextThemeWrapper(this, R.style.default1);
        TextView t = (TextView) findViewById(R.id.text1);
        drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_hangman,  themeWapper.getTheme());
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageDrawable(drawable);

        b.setOnClickListener((userGuess) -> {
                Resources.Theme theme = getResources().newTheme();
                theme.applyStyle(R.style.head, true);
                changeTheme(theme);

                t.setText("This Looks NICE");

        } );

    }

    private void changeTheme(Resources.Theme theme) {
        final Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_hangman, theme);
        imageView.setImageDrawable(drawable);
    }



}
