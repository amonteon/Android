package com.example.alejandramonteon.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "mainactivity";
    private ProgressBar progress;
    private EditText search;
    private RecyclerView recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = (EditText) findViewById(R.id.searchMenuItem);
        recycle = (RecyclerView)findViewById(R.id.recyclerView);
        recycle.setLayoutManager(new LinearLayoutManager(this));
    }

    class NetworkTask extends AsyncTask<URL, Void, ArrayList<News>> {
        String query;

        NetworkTask(String s) {
            query = s;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setVisibility(View.VISIBLE);

        }

        @Override
        protected ArrayList<News> doInBackground(URL... params) {
            ArrayList<News> result = null;
            URL url = NetworkUtils.createURL(query, "latest", "04be58284b3b47be99d63ece1b582ae3");
            Log.d(TAG, "url: " + url.toString());
            try {
                String json = NetworkUtils.getResponseFromHttpUrl(url);
                result = NetworkUtils.parseJSON(json);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(final ArrayList<News> data) {
            super.onPostExecute(data);
            progress.setVisibility(View.GONE);
            if (data != null) {
                ItemView adapter = new ItemView(data, new ItemView.ItemClickListener() {
                    @Override
                    public void onItemClick(int clickedItemIndex) {
                        String url = data.get(clickedItemIndex).getUrl();
                        Log.d(TAG, String.format("Url %s", url));
                    }
                });
                recycle.setAdapter(adapter);

            }
        }
    }
}
