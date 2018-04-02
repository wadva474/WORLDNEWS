package com.example.abdul_wadudmusa.drawer.fragments;


import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;

import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.abdul_wadudmusa.drawer.Connection;
import com.example.abdul_wadudmusa.drawer.News;
import com.example.abdul_wadudmusa.drawer.NewsAdapter;
import com.example.abdul_wadudmusa.drawer.R;
import com.example.abdul_wadudmusa.drawer.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Business extends android.support.v4.app.Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<List<News>> {
 ArrayList<News>business=new ArrayList<>();
    RecyclerView bus;

    @Override
    public android.support.v4.content.Loader<List<News>> onCreateLoader(int id, Bundle args) {
        waddy.setVisibility(View.VISIBLE);
        return  new Connection(getContext());

    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<List<News>> loader, List<News> data) {
        waddy.setVisibility(View.INVISIBLE);
        business.clear();
        business.addAll(data);}

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<List<News>> loader) {

    }

    ProgressBar waddy;
private static int First_Loader=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
       View k=inflater.inflate(R.layout.business, container, false);
        bus= k.findViewById(R.id.business);
        waddy =k.findViewById(R.id.progressdialogb);

        LinearLayoutManager BusinessManager=new LinearLayoutManager(getContext());
        bus.setLayoutManager(BusinessManager);
        RecyclerAdapter BusinessAdater=new RecyclerAdapter(business,getContext());
        bus.setAdapter(BusinessAdater);
        getLoaderManager().initLoader(First_Loader,null, this).forceLoad();
        BusinessAdater.notifyDataSetChanged();
        bus.setHasFixedSize(true);

          return k;
    }
}


