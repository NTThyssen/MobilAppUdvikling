package com.tt.nicklas.myappbrint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.StartBtn);
        button.setOnClickListener((start) -> {
            startNewActivity();
        });
    }

   public void startNewActivity(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);

    }
}
