package com.example.abdul_wadudmusa.drawer.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    public static Retrofit getRetrofit(){
       return  new Retrofit.Builder().baseUrl(Base_Url.Base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

}
