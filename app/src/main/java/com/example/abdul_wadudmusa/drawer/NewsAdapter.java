package com.example.abdul_wadudmusa.drawer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import com.squareup.picasso.Picasso;

public class NewsAdapter extends RecyclerView.ViewHolder {
    TextView mSource,mDate,mContent;
    ImageView mImage;
    CardView mCardView;

    public NewsAdapter(View itemView) {
        super(itemView);
        this.mSource =itemView.findViewById(R.id.textView3);
        this.mDate = itemView.findViewById(R.id.textView4);
        this.mContent = itemView.findViewById(R.id.Textview);
        this.mCardView = itemView.findViewById(R.id.cardView);
        this.mImage=itemView.findViewById(R.id.imageview);

    }
}

