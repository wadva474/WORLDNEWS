package com.example.abdul_wadudmusa.drawer;


import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.abdul_wadudmusa.drawer.MainActivity.Country;
import static com.example.abdul_wadudmusa.drawer.Technology.wadva;
import static com.example.abdul_wadudmusa.drawer.Connection.createUrl;
import static com.example.abdul_wadudmusa.drawer.Connection.extractFeatureFromJson;
import static com.example.abdul_wadudmusa.drawer.Connection.makeHttpRequest;


/**
 * A simple {@link Fragment} subclass.
 */
public class Technology extends Fragment {
    public static ArrayList<News>wadva;
    private static ProgressBar wad;
    public Technology() {
        wadva=new ArrayList<News>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View k = inflater.inflate(R.layout.technology,container,false);
        ListView w = k.findViewById(R.id.listviewfortech);
        wad= k.findViewById(R.id.progssdialogt);
        new connectifyfortech().execute();
        w.setAdapter(new NewsAdapter(getContext().getApplicationContext(),wadva));
        w.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news =wadva.get(position);
                Intent wadva = new Intent(Intent.ACTION_VIEW);
                wadva.setData(Uri.parse(news.getWebpage()));
                startActivity(wadva);
            }
        });
        return k;
    }
    public  class connectifyfortech extends AsyncTask<URL,Void,ArrayList<News>> {
        @Override
        protected ArrayList<News> doInBackground(URL... urls) {
            URL url =createUrl("https://newsapi.org/v2/top-headlines?country=" +Country+"&language=english&category=technology&apiKey=ac31ae40aa5e47e58965e335c63ec110");
            String jsonResponse = "";
            try {
                jsonResponse =makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }
            return extractFeatureFromJson(jsonResponse);
        }


        @Override
        protected void onPostExecute(ArrayList<News> news) {
            publishProgress();
            if (news == null) {
                return;
            }
            wadva=news;
            wad.setVisibility(View.GONE);
        }
    }
    }



