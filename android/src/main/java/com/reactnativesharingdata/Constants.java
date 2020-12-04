package com.reactnativesharingdata;

import java.util.function.Function;

import android.net.Uri;
import android.provider.BaseColumns;

public class Constants {

    public static String AUTHORITY = "com.reactnativesharingdata";
    public static String PATH  = "/data";
    public static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + PATH);

    public static void initUri(String auth, String path) {
        AUTHORITY = auth;
        PATH = path;
        CONTENT_URI = Uri.parse("content://" + auth + path);
        ShareContentProvider.setUriMatcher(auth, path);
    }


    public static final String CONTENT_LIST = "vnd.android.cursor.dir/multi";
    public static final String CONTENT_ITEM = "vnd.android.cursor.item/single";

    public static final String DATABASE_NAME = "share_content_db";
    public static final int DATABASE_VERSION = 2;


    public static class Content implements BaseColumns {

        private Content(){}

        public static final String TABLE_NAME = "share_content";

        public static final String ID = "_id";
        public static final String CONTENT_NAME = "content_name";
        public static final String CONTENT_VALUE = "content_value";

    }
}
