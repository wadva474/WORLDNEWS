package com.example.abdul_wadudmusa.drawer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(@NonNull Context context, ArrayList<News> news) {
        super(context, 0,news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View k = convertView;
        if (k == null) {
            k = LayoutInflater.from(parent.getContext()).inflate(R.layout.xmlforlistview, parent, false);
        }
        News current =getItem(position);
        ImageView a = k.findViewById(R.id.imageview);
        try {
            Picasso.with(parent.getContext()).load(current.getImageUrl()).into(a);
        }
        catch (Exception e){
            Log.e("ERROR","Error loading image url");
        }

        TextView b= k.findViewById(R.id.Textview);
        b.setText(current.getTopic());
        return k;
    }
}

