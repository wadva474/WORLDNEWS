package com.example.abdul_wadudmusa.drawer.fragments;


import android.app.LoaderManager;
import android.arch.lifecycle.Lifecycle;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.abdul_wadudmusa.drawer.News;
import com.example.abdul_wadudmusa.drawer.NewsAdapter;
import com.example.abdul_wadudmusa.drawer.R;
import com.example.abdul_wadudmusa.drawer.RecyclerAdapter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.abdul_wadudmusa.drawer.MainActivity.Country;
import static com.example.abdul_wadudmusa.drawer.Connection.createUrl;
import static com.example.abdul_wadudmusa.drawer.Connection.extractFeatureFromJson;
import static com.example.abdul_wadudmusa.drawer.Connection.makeHttpRequest;


/**
 * A simple {@link Fragment} subclass.
 */
public class Technology extends Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<ArrayList<News>> {
     ArrayList<News>Technology=new ArrayList<>();
    ProgressBar wad;
    RecyclerView RecyclerTech;
    RecyclerAdapter Adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View k = inflater.inflate(R.layout.technology,container,false);
       RecyclerTech = k.findViewById(R.id.listviewfortech);
        wad= k.findViewById(R.id.progssdialogt);
        LinearLayoutManager TechManager=new LinearLayoutManager(getContext());
        RecyclerTech.setLayoutManager(TechManager);
        Adapter =new RecyclerAdapter(Technology,getContext());
        RecyclerTech.setAdapter(Adapter);
        getLoaderManager().initLoader(1,null,  this).forceLoad();
        Adapter.notifyDataSetChanged();
        RecyclerTech.setHasFixedSize(true);
        return k;
    }

    @Override
    public android.support.v4.content.Loader<ArrayList<News>> onCreateLoader(int id, Bundle args) {
        wad.setVisibility(View.VISIBLE);
        return new connectifyfortech(getContext());
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<ArrayList<News>> loader, ArrayList<News> data) {
        wad.setVisibility(View.INVISIBLE);
        Technology.clear();
        Technology.addAll(data);
    }


    @Override
    public void onLoaderReset(android.support.v4.content.Loader<ArrayList<News>> loader) {


    }


    public static class connectifyfortech extends android.support.v4.content.AsyncTaskLoader<ArrayList<News>> {

        private connectifyfortech(Context context) {
            super(context);
        }



        @Override
        public ArrayList<News> loadInBackground() {
            URL url = createUrl("https://newsapi.org/v2/top-headlines?country=" + Country + "&language=english&category=technology&apiKey=ac31ae40aa5e47e58965e335c63ec110");
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }
            return extractFeatureFromJson(jsonResponse);
        }
    }
    }



