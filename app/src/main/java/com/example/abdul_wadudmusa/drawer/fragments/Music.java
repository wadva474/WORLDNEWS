package com.example.abdul_wadudmusa.drawer.fragments;


import android.app.Fragment;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.abdul_wadudmusa.drawer.MainActivity;
import com.example.abdul_wadudmusa.drawer.News;
import com.example.abdul_wadudmusa.drawer.NewsAdapter;
import com.example.abdul_wadudmusa.drawer.R;
import com.example.abdul_wadudmusa.drawer.RecyclerAdapter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.abdul_wadudmusa.drawer.Connection.createUrl;
import static com.example.abdul_wadudmusa.drawer.Connection.extractFeatureFromJson;
import static com.example.abdul_wadudmusa.drawer.Connection.makeHttpRequest;


/**
 * A simple {@link Fragment} subclass.
 */
public class Music extends android.support.v4.app.Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<List<News>> {
    ArrayList<News> wadva=new ArrayList<>();
    ProgressBar wad;
    RecyclerView w;


    public Music() {
     wadva=new ArrayList<>();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View k = inflater.inflate(R.layout.music,container,false);
        wad= k.findViewById(R.id.progressbarm);
      w = k.findViewById(R.id.listformusic);
        LinearLayoutManager Music = new LinearLayoutManager(getContext());
        w.setLayoutManager(Music);
        RecyclerAdapter MusicAdapter=new RecyclerAdapter(wadva,getContext());
        w.setAdapter(MusicAdapter);
        getLoaderManager().initLoader(3,null,  this).forceLoad();
        MusicAdapter.notifyDataSetChanged();
        w.setHasFixedSize(true);
        return k;
    }


    @Override
    public android.support.v4.content.Loader<List<News>> onCreateLoader(int id, Bundle args) {
        wad.setVisibility(View.VISIBLE);
        return new connectifyfortech(getContext());
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<List<News>> loader, List<News> data) {
        wad.setVisibility(View.INVISIBLE);
        wadva.clear();
        wadva.addAll(data);

    }


    @Override
    public void onLoaderReset(android.support.v4.content.Loader loader) {

    }


    public static   class connectifyfortech extends android.support.v4.content.AsyncTaskLoader<List<News>>{

        public connectifyfortech(Context context) {
            super(context);
        }

        @Override
        public List<News> loadInBackground() {
            URL url =createUrl("https://newsapi.org/v2/top-headlines?country=" + MainActivity.Country+"&language=english&category=music&apiKey=ac31ae40aa5e47e58965e335c63ec110");
            String jsonResponse = "";
            try {
                jsonResponse =makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }
            return  extractFeatureFromJson(jsonResponse);

        }
    }
    }


