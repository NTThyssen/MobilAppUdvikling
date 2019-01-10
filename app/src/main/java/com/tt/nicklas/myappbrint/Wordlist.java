package com.tt.nicklas.myappbrint;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Wordlist extends AppCompatActivity {
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordlist);
        GameLogic gameLogic = new GameLogic();
        lv = findViewById(R.id.listOfWords);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                gameLogic.getPossibleWords());
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... arg0) {
                try {

                    gameLogic.retrieveWordsFromDr();

                    return "";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Ordene blev ikke hentet korrekt: " + e;
                }
            }

            @Override
            protected void onPostExecute(Object resultat) {
                lv.setAdapter(arrayAdapter);

            }
        }.execute();

        Bundle bundle = new Bundle();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                bundle.putString("wordToGuess", ((TextView) view).getText().toString());

            }
        });



    }
}
