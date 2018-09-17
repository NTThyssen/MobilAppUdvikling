package com.tt.nicklas.myappbrint;

import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainMenu extends AppCompatActivity {
    boolean changeTheme = true;
    Drawable drawable;
    ContextThemeWrapper wrapper1;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Button b = (Button) findViewById(R.id.button);
        wrapper1 = new ContextThemeWrapper(this, R.style.default1);
        TextView t = (TextView) findViewById(R.id.text1);
        drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_hangman, wrapper1.getTheme());
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageDrawable(drawable);

        b.setOnClickListener((v) -> {
            if(changeTheme){
                wrapper1 = new ContextThemeWrapper(this, R.style.rope);
                drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_hangman, wrapper1.getTheme());
                imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageDrawable(drawable);
                changeTheme = false;
                t.setText("This Looks CREEPY!");
            }else{
                t.setText("This Looks NICE");
                wrapper1 = new ContextThemeWrapper(this, R.style.default1);
                drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_hangman, wrapper1.getTheme());
                imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageDrawable(drawable);
                changeTheme = true;
            }

        } );

    }


}
