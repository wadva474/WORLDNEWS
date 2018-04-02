package com.example.abdul_wadudmusa.drawer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<NewsAdapter>{
    ArrayList<News> ListOfNews;
    Context context;

    public RecyclerAdapter(ArrayList<News> listOfNews, Context context) {
        ListOfNews = listOfNews;
        this.context = context;
    }

    @Override
    public NewsAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.xmlforlistview,parent,false);
        return new NewsAdapter(view);
    }

    @Override
    public void onBindViewHolder(final NewsAdapter holder, final int position) {
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webpage=new Intent(Intent.ACTION_VIEW);
                webpage.setData(Uri.parse(ListOfNews.get(position).getWebpage()));
                context.startActivity(webpage);
            }
        });
        holder.mContent.setText(ListOfNews.get(position).getTopic());
        Picasso.with(context).load(ListOfNews.get(position).getImageUrl()).into(holder.mImage);


    }

    @Override
    public int getItemCount() {
        if (ListOfNews != null) {
            return ListOfNews.size();
        } else {
            return 0;
        }
    }

}
