package com.tt.nicklas.myappbrint;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ThemesAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> priceList;
    private ArrayList<Integer> listId;

    ShopActivity shopActivity = new ShopActivity();
    public ThemesAdapter(Context context, ArrayList<Integer> listId, ArrayList<String> priceList){

    }

    @Override
    public int getCount() {
        return priceList.size();
    }

    @Override
    public Object getItem(int i) {
        return priceList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            view = View.inflate(context, R.layout.custom_list, null);
        }
//does not work yet
        ImageView img = (ImageView)view.findViewById(R.id.listImg);
        TextView txt = (TextView)view.findViewById(R.id.priceText);
        img.setImageResource(listId.get(i));
        txt.setText(priceList.get(i));
        return view;
    }
}
