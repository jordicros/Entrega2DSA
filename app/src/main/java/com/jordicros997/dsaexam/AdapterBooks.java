package com.jordicros997.dsaexam;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordi on 30/05/2018.
 */

public class AdapterBooks extends BaseAdapter {

    protected Context context;
    protected List<Llibre> llibres;
    public AdapterBooks(Activity context, List<Llibre> lbs)
    {
        this.context = context;
        this.llibres = lbs;
    }
    @Override
    public int getCount() {
        return llibres.size();
    }
    public void clear(){
        llibres.clear();
    }
    public void addAll(ArrayList<Llibre> grups)
    {
        for(int i=0;i<grups.size();i++){
            this.llibres.add(grups.get(i));
        }
    }
    @Override
    public Object getItem(int i) {
        return llibres.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view = LayoutInflater.from(context).
                    inflate(R.layout.book_viewer,viewGroup, false);
        }
        Llibre ll =(Llibre) getItem(i);
        TextView title = view.findViewById(R.id.textView2);
        title.setText(ll.title);
        TextView author = view.findViewById(R.id.textView3);
        title.setText(ll.title);
        ImageView img = view.findViewById(R.id.imageView2);
        Picasso.get().load(ll.image).into(img);
        return view;
    }
}

