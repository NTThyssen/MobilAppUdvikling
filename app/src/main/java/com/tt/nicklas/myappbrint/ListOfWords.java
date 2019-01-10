package com.tt.nicklas.myappbrint;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListOfWords.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListOfWords#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListOfWords extends Fragment {
    private TextView txt;
    private ListView lv;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_of_words, container, false);
        GameLogic gameLogic = new GameLogic();
        lv = view.findViewById(R.id.listWords);
        txt = view.findViewById(R.id.choseWord);
        txt.setText("Henter Ord Fra DR");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,
                gameLogic.getPossibleWords());
        if(!GlobalVaribles.wordsFromdrDone) {
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
                    txt.setText("Vælg et ord fra listen og brug det");
                }
            }.execute();
        }else{
            lv.setAdapter(arrayAdapter);
            txt.setText("Vælg et ord fra listen og brug det");
        }
        Bundle bundle = new Bundle();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                getFragmentManager().beginTransaction().replace(R.id.is_fragment_container, new GameFragment()).addToBackStack(null).commit();
               GlobalVaribles.setChosenWord(((TextView) view).getText().toString());
            }
        });


        return view;
    }



}
