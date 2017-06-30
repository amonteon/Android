package com.example.alejandramonteon.newsapp;




/**
 * Created by alejandramonteon on 6/18/17.
 */

public class NetworkUtils {

    private static final String BASE_URL =
            "https://newsapi.org/v1/articles";
    public static final String PARAM_SORT = "sort";
    public static final String PARAM_SOURCE = "source";
    public static final String PARAM_APIKEY = "apiKey";
    public static final String TAG = "NetworkUtils";

    public static java.net.URL createURL(java.lang.String searchQuery, java.lang.String sortBy, String api){
        android.net.Uri uri = android.net.Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_SOURCE, searchQuery)
                .appendQueryParameter(PARAM_SORT, sortBy)
                .appendQueryParameter(PARAM_APIKEY, api).build();

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

    public static java.util.ArrayList<News> parseJSON(String json) throws org.json.JSONException{
        java.util.ArrayList<News> result = new java.util.ArrayList<>();
        org.json.JSONObject main = new org.json.JSONObject(json);

        org.json.JSONArray items = main.getJSONArray("items");

        for(int i = 0; i < items.length(); i++){
            org.json.JSONObject item = items.getJSONObject(i);
            String article = item.getString("articles");
            org.json.JSONObject source = item.getJSONObject("source");
            String author = source.getString("author");
            String title = source.getString("title");
            String url = item.getString("url");
            String description = source.getString("description");
            String urlToImage = source.getString("urlToImage");
            String publishedAt = source.getString("publishedAt");
            News news = new News(author, title, description, url, urlToImage, publishedAt);
            result.add(news);
        }
        return result;
    }

}





