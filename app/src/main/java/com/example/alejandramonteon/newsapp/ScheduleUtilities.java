package com.example.alejandramonteon.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

// This class refreshes the News App every 60 seconds
// Got this class from Mark's repo

public class ScheduleUtilities {
    private static final int SCHEDULE_INTERVAL_MINUTES = 360;
    private static final int SYNC_FLEXTIME_SECONDS = 60;
    private static final String NEWS_JOB_TAG = "news_job_tag";

    private static boolean sInitialized;

    synchronized public static void scheduleRefresh(@NonNull final Context context){
        if(sInitialized) return;

        // Create a new dispatcher using the Google Play driver.
        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        Job constraintRefreshJob = dispatcher.newJobBuilder()
                // the JobService that will be called
                .setService(NewsJob.class)
                // uniquely identifies the job
                .setTag(NEWS_JOB_TAG)
                // Can run on any network
                .setConstraints(Constraint.ON_ANY_NETWORK)
                // persist forever
                .setLifetime(Lifetime.FOREVER)
                //set off-job to true
                .setRecurring(true)
                // start between 0 and 60 seconds
                .setTrigger(Trigger.executionWindow(SCHEDULE_INTERVAL_MINUTES,
                        SCHEDULE_INTERVAL_MINUTES + SYNC_FLEXTIME_SECONDS))
                // overwrite an existing job with the same tag
                .setReplaceCurrent(true)
                .build();

        dispatcher.schedule(constraintRefreshJob);
        sInitialized = true;

    }

}
