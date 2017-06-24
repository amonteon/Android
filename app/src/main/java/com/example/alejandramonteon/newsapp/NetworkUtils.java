package com.example.alejandramonteon.newsapp;

/**
 * Created by alejandramonteon on 6/18/17.
 */

public class NetworkUtils {

    private static final String BASE_URL =
            "https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=04be58284b3b47be99d63ece1b582ae3";

    public static final String PARAM_SORT = "sort";
    public static final String PARAM_SOURCE = "source";
    public static final String TAG = "NetworkUtils";

    public static java.net.URL createURL(java.lang.String searchQuery, java.lang.String sortBy){
        android.net.Uri uri = android.net.Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_SOURCE, searchQuery)
                .appendQueryParameter(PARAM_SORT, sortBy).build();

        java.net.URL url = null;
        try {
            String urlString = uri.toString();
            android.util.Log.d(TAG, "Url: " + urlString);
            url = new java.net.URL(uri.toString());
        } catch (java.net.MalformedURLException e) {
            e.printStackTrace();
        }

        return url;

    }


    public static String getResponseFromHttpUrl(java.net.URL url) throws java.io.IOException {
        java.net.HttpURLConnection urlConnection = (java.net.HttpURLConnection) url.openConnection();
        try {
            java.io.InputStream in = urlConnection.getInputStream();
            java.util.Scanner input = new java.util.Scanner(in);

            input.useDelimiter("\\A");
            return (input.hasNext()) ? input.next() : null;

        } finally {
            urlConnection.disconnect();
        }
    }




}
