package com.tt.nicklas.myappbrint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Button b = (Button) findViewById(R.id.button);
        TextView t = (TextView) findViewById(R.id.text1);
        b.setOnClickListener((v) -> {
            t.setText("this is new text");
        } );

    }


}
