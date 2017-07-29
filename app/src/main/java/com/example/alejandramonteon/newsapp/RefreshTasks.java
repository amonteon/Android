package com.example.alejandramonteon.newsapp;

/**
 * Created by alejandramonteon on 7/27/17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.alejandramonteon.newsapp.data.NewsItem;
import com.example.alejandramonteon.newsapp.data.DatabaseUtils;
import com.example.alejandramonteon.newsapp.data.DBHelper;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

// Class refreshes url to display most current articles
public class RefreshTasks {

    public static final String ACTION_REFRESH = "refresh";


    public static void refreshArticles(Context context) {
        ArrayList<NewsItem> result = null;
        URL url = NetworkUtils.makeURL();

        SQLiteDatabase db = new com.example.alejandramonteon.newsapp.data.DBHelper(context).getWritableDatabase();

        try {
            DatabaseUtils.deleteAll(db);
            String json = NetworkUtils.getResponseFromHttpUrl(url);
            result = NetworkUtils.parseJSON(json);
            DatabaseUtils.bulkInsert(db, result);

        } catch (IOException e) {
            e.printStackTrace();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        db.close();
    }
}