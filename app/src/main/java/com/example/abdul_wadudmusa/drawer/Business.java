package com.example.abdul_wadudmusa.drawer;


import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Business extends Fragment {
public static ArrayList<News>business;
public static ProgressBar waddy;

    public Business() {
       business=new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
       View k=inflater.inflate(R.layout.business, container, false);
      ListView bus = k.findViewById(R.id.business);
        waddy =k.findViewById(R.id.progressdialogb);
        new Connection().execute();
     bus.setAdapter(new NewsAdapter(getContext().getApplicationContext(),business));
     bus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             News news =business.get(position);
             Intent wadva =new Intent(Intent.ACTION_VIEW);
             wadva.setData(Uri.parse(news.getWebpage()));
             startActivity(wadva);
         }
     });
          return k;
    }

}


