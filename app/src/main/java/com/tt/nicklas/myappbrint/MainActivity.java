package com.tt.nicklas.myappbrint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button shopBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.StartBtn);
        button.setOnClickListener((start) -> {
            startNewActivity();
        });

        shopBtn = findViewById(R.id.shop);
        shopBtn.setOnClickListener((start) -> {
            startShopActivity();
        });
    }

   public void startNewActivity(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);

    }

    public void startShopActivity(){
        Intent intent = new Intent(this, ShopActivity.class);
        startActivity(intent);

    }
}
