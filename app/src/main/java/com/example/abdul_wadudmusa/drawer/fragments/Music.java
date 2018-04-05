package com.example.abdul_wadudmusa.drawer.fragments;


import android.app.Fragment;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.abdul_wadudmusa.drawer.Apicall.Getrequest;
import com.example.abdul_wadudmusa.drawer.Constant.RetrofitBuilder;
import com.example.abdul_wadudmusa.drawer.MainActivity;
import com.example.abdul_wadudmusa.drawer.R;
import com.example.abdul_wadudmusa.drawer.RecyclerAdapter;
import com.example.abdul_wadudmusa.drawer.model.Article;
import com.example.abdul_wadudmusa.drawer.model.ResponseLogin;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class Music extends android.support.v4.app.Fragment {
    ArrayList<Article> wadva=new ArrayList<>();
    ProgressBar wad;
    RecyclerView w;


    public Music() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View k = inflater.inflate(R.layout.music,container,false);
        wad= k.findViewById(R.id.progressbarm);
      w = k.findViewById(R.id.listformusic);
        LinearLayoutManager Music = new LinearLayoutManager(getContext());
        w.setLayoutManager(Music);
        Retrofit retrofit= RetrofitBuilder.getRetrofit();
        Getrequest getrequest=retrofit.create(Getrequest.class);
        Call<ResponseLogin> News=getrequest.headlines(MainActivity.Country,"ac31ae40aa5e47e58965e335c63ec110" ,"music");
        News.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin>call, Response<ResponseLogin>response) {
                wad.setVisibility(View.INVISIBLE);
                wad.bringToFront();
                wadva.clear();
                if (response.body()!=null)
                    wadva.addAll(response.body().getArticles());
            }

            @Override
            public void onFailure(Call<ResponseLogin>call, Throwable t) {

            }
        });
        RecyclerAdapter MusicAdapter=new RecyclerAdapter(wadva,getContext());
        w.setAdapter(MusicAdapter);
        MusicAdapter.notifyDataSetChanged();
        w.setHasFixedSize(true);
        return k;
    }
    }


