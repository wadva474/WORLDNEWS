package com.example.abdul_wadudmusa.drawer.Apicall;

import com.example.abdul_wadudmusa.drawer.model.ResponseLogin;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Getrequest {
    @GET("top-headlines")
    Call<ResponseLogin> headlines (@Query("country")String country, @Query("apikey")String apikey, @Query("category")String category);

}
