package com.example.abdul_wadudmusa.drawer;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static com.example.abdul_wadudmusa.drawer.MainActivity.Country;
import static com.example.abdul_wadudmusa.drawer.Business.business;
import static com.example.abdul_wadudmusa.drawer.Business.waddy;


public  class Connection extends AsyncTask<URL,Void,ArrayList<News>>{
    public Connection() {

    }

    @Override
    protected ArrayList<News> doInBackground(URL... urls) {
        URL url = createUrl("https://newsapi.org/v2/top-headlines?country=" +Country+"&language=english&category=business&apiKey=ac31ae40aa5e47e58965e335c63ec110");

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            // TODO Handle the IOException
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object

        // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
        return extractFeatureFromJson(jsonResponse);
    }
    @Override
    protected void onPostExecute(ArrayList<News> news) {
        if (news == null) {
            return;

        }

        business=news;
        waddy.setVisibility(View.GONE);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        publishProgress();
        super.onProgressUpdate(values);
    }

    public static URL createUrl(String stringUrl) {
        URL url = null;
        try {

            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("ERROR", "Error with creating URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
           urlConnection.setConnectTimeout(10000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.setUseCaches(false);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("ERROR", "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (Exception e) {
            Log.e("ERROR", "Problem retrieving the news JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    public static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    public static ArrayList<News> extractFeatureFromJson(String news) {
        ArrayList<News> wad =new ArrayList<>();
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(news)) {
            return null;
        }

        try {
            JSONObject baseJsonResponse = new JSONObject(news);
            JSONArray featureArray = baseJsonResponse.getJSONArray("articles");

            for (int i = 0; i <featureArray.length(); i++) {
                JSONObject firstFeature = featureArray.getJSONObject(i);
                String topic = firstFeature.getString("title");
                String webpage = firstFeature.getString("url");
                String imageurl = firstFeature.getString("urlToImage");
                wad.add(new News(topic,webpage,imageurl));
            }
            return wad;
        } catch (JSONException e) {
            Log.e("ERROR", "Problem parsing the news JSON results", e);
        }
        return null;
    }



}


