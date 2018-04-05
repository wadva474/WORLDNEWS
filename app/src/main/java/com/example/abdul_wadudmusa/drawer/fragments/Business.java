package com.example.abdul_wadudmusa.drawer.fragments;


import android.app.Fragment;

import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class Business extends android.support.v4.app.Fragment  {
 ArrayList<Article>business=new ArrayList<>();
    RecyclerView bus;


    ProgressBar waddy;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
       View k=inflater.inflate(R.layout.business, container, false);
        bus= k.findViewById(R.id.business);
        waddy =k.findViewById(R.id.progressdialogb);

        LinearLayoutManager BusinessManager=new LinearLayoutManager(getContext());
        bus.setLayoutManager(BusinessManager);
        Retrofit retrofit=RetrofitBuilder.getRetrofit();
        Getrequest getrequest=retrofit.create(Getrequest.class);
        Call<ResponseLogin> News=getrequest.headlines(MainActivity.Country,"ac31ae40aa5e47e58965e335c63ec110" ,"business");
        News.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin>response) {

                waddy.setVisibility(View.INVISIBLE);
                waddy.bringToFront();
                if (response.body()!=null) {
                    business.clear();
                    business.addAll(response.body().getArticles());
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Log.e(TAG, "onFailure: Parsing",t );
            }
        });
        RecyclerAdapter BusinessAdater=new RecyclerAdapter(business,getContext());
        bus.setAdapter(BusinessAdater);
        BusinessAdater.notifyDataSetChanged();
        bus.setHasFixedSize(true);
          return k;
    }
}


