package com.jwelch.android.numb3rfacts.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by jwelch on 3/4/15.
 */
public class NumbersContract {

    public static final String CONTENT_AUTHORITY = "com.jwelch.android.numb3rfacts.app";

    // Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    // the content provider.
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // Possible paths (appended to base content URI for possible URI's)
    // For instance, content://com.example.android.sunshine.app/weather/ is a valid path for
    // looking at weather data. content://com.example.android.sunshine.app/givemeroot/ will fail,
    // as the ContentProvider hasn't been given any information on what to do with "givemeroot".
    // At least, let's hope not.  Don't be that dev, reader.  Don't be that dev.
    public static final String PATH_FACT = "fact";

    public static final class FactEntry implements BaseColumns {
        public static final String TABLE_NAME = "fact";
        public static final String COLUMN_TEXT = "text";
        public static final String COLUMN_YEAR = "year";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_NUMBER = "number";
        public static final String COLUMN_FOUND = "found";
        public static final String COLUMN_TYPE = "type";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FACT).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FACT;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FACT;

        public static Uri buildFactUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildFactUriWithType(String type) {
            return CONTENT_URI.buildUpon().appendPath(type).build();
        }

        public static Uri buildFactUriWithTypeAndNumber(String type, int number) {
            return CONTENT_URI.buildUpon().appendPath(type).appendQueryParameter(COLUMN_NUMBER, String.valueOf(number)).build();
        }

        public static String getTypeFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static int getNumberFromUri(Uri uri) {
            return Integer.valueOf(uri.getQueryParameter(COLUMN_NUMBER));
        }

    }
}
