package com.example.alejandramonteon.newsapp;


import com.example.alejandramonteon.newsapp.data.NewsItem;

/**
 * Created by alejandramonteon on 6/18/17.
 */

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


public class NetworkUtils {
    public static final String TAG = "NetworkUtils";

    public static final String GITHUB_BASE_URL =
            "https://newsapi.org/v1/articles?";
    public static final String PARAM_SOURCE = "source";
    public static final String PARAM_SORTBY = "sortBy";
    public static final String PARAM_API_KEY= "apiKey";

    // Creates url based on base and api key
    public static URL makeURL() {
        Uri uri = Uri.parse(GITHUB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_SOURCE, "the-next-web")
                .appendQueryParameter(PARAM_SORTBY, "latest")
                .appendQueryParameter(PARAM_API_KEY, "04be58284b3b47be99d63ece1b582ae3")
                .build();

        URL url = null;
        try {
            String urlString = uri.toString();
            Log.d(TAG, "Url: " + urlString);
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
    // Setup http connection
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner input = new Scanner(in);

            input.useDelimiter("\\A");
            String result = (input.hasNext()) ? input.next() : null;
            return result;

        }catch (IOException e){
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return null;
    }
// JSON Data parser
    public static ArrayList<NewsItem> parseJSON(String json) throws JSONException {
        ArrayList<NewsItem> result = new ArrayList<>();
        JSONObject main = new JSONObject(json);
        // store articles in json object
        JSONArray items = main.getJSONArray("articles");
        String imgUrl = null;

        for(int i = 0; i < items.length(); i++){
            JSONObject item = items.getJSONObject(i);
            // store json article: title, date, description, url and image into array list
            String author = item.getString("author");
            String title = item.getString("title");
            String description = item.getString("description");
            String url = item.getString("url");
            String publishedDate = item.getString("published_date");

            // Store Images
            JSONArray mediaObjects = item.optJSONArray("media");
            if(mediaObjects != null){
                JSONObject img = mediaObjects.getJSONObject(0);
                JSONArray metaData = img.getJSONArray("media-metadata");
                JSONObject thumbNailMeta = metaData.getJSONObject(0);
                imgUrl = thumbNailMeta.getString("urlToImage");
            }

            result.add(new NewsItem(author, title, description, url, publishedDate, imgUrl));

        }
        Log.d(TAG, "final articles size: " + result.size());
        return result;
    }


}