package com.jordicros997.dsaexam;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordi on 30/05/2018.
 */

public class AdapterComments extends BaseAdapter {
    protected Context context;
    protected List<Comment> comentaris;
    public AdapterComments(Activity context, List<Comment> lbs)
    {
        this.context = context;
        this.comentaris = lbs;
    }
    @Override
    public int getCount() {
        return comentaris.size();
    }
    public void clear(){
        comentaris.clear();
    }
    public void addAll(ArrayList<Comment> cmts)
    {
        for(int i=0;i<cmts.size();i++){
            this.comentaris.add(cmts.get(i));
        }
    }
    @Override
    public Object getItem(int i) {
        return comentaris.get(i);
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
                    inflate(R.layout.comments_viewer,viewGroup, false);
        }
        Comment ll =(Comment) getItem(i);
        TextView user = view.findViewById(R.id.textView5);
        user.setText(ll.user);
        TextView comment = view.findViewById(R.id.textView3);
        comment.setText(ll.message);

        return view;
    }
}
