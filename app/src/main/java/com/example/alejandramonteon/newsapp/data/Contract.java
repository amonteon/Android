package com.example.alejandramonteon.newsapp.data;

/**
 * Created by alejandramonteon on 7/28/17.
 */

import android.provider.BaseColumns;


public class Contract {

    public static class TABLE_ARTICLES implements BaseColumns {
        public static final String TABLE_NAME = "articles";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_PUBLISHED_DATE = "published_date";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_URLTOIMG = "urlToImage";
        public static final String COLUMN_NAME_URL = "url";
    }
}
