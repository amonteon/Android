package com.example.alejandramonteon.newsapp;

/**
 * Created by alejandramonteon on 7/28/17.
 */

import android.os.AsyncTask;
import android.widget.Toast;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

// News Job class will be called and used as a Job service in ScheduleUtilities
public class NewsJob extends JobService {
    AsyncTask mBackgroundTask;

    @Override
    // On app start it will take a job
    public boolean onStartJob(final JobParameters job) {
        // Load asynctask
        mBackgroundTask = new AsyncTask() {
            @Override
            protected void onPreExecute() {
                // before job execution a toast will appear everytime the app has been refreshed
                Toast.makeText(NewsJob.this, "Articles have been refreshed", Toast.LENGTH_SHORT).show();
                super.onPreExecute();
            }

            @Override
            // On the background the app is refreshing the news articles through the job service
            protected Object doInBackground(Object[] params) {
                RefreshTasks.refreshArticles(NewsJob.this);
                return null;
            }

            @Override
            // Once the job us finished then the results will appear
            protected void onPostExecute(Object o) {
                jobFinished(job, false);
                super.onPostExecute(o);

            }
        };


        mBackgroundTask.execute();

        return true;
    }

    @Override
    // If the app/job is inteerupted then that job cancelled
    public boolean onStopJob(JobParameters job) {

        if (mBackgroundTask != null) mBackgroundTask.cancel(false);

        return true;
    }
}