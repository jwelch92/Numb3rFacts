package com.jwelch.android.numb3rfacts.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * Created by jwelch on 3/4/15.
 */
public class NumberProvider extends ContentProvider {
    private static final String TAG = NumberProvider.class.getSimpleName();
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private NumbersDBHelper mOpenHelper;

    static final int FACT = 100;
//    static final int FACT_WITH_NUMBER = 101;

    static UriMatcher buildUriMatcher() {
        // 1) The code passed into the constructor represents the code to return for the root
        // URI.  It's common to use NO_MATCH as the code for this case. Add the constructor below.
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = NumbersContract.CONTENT_AUTHORITY;
        // 2) Use the addURI function to match each of the types.  Use the constants from
        // WeatherContract to help define the types to the UriMatcher.
        uriMatcher.addURI(authority, NumbersContract.PATH_FACT, FACT);
//        uriMatcher.addURI(authority, NumbersContract.PATH_FACT + "/*", FACT_WITH_NUMBER);
        // 3) Return the new matcher!
        return uriMatcher;
    }



    @Override
    public boolean onCreate() {
        mOpenHelper = new NumbersDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor = null;

        switch (sUriMatcher.match(uri)) {
            case FACT:
                retCursor = mOpenHelper.getReadableDatabase().query(
                        NumbersContract.FactEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
        }
        return retCursor;
    }

    @Override
    public String getType(Uri uri) {
        switch(sUriMatcher.match(uri)) {
            case FACT:
                return NumbersContract.FactEntry.CONTENT_TYPE;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri = null;
        switch (match) {
            case FACT: {
                long _id = db.insert(NumbersContract.FactEntry.TABLE_NAME, null, values);
                Log.v(TAG, "Row id is: " + String.valueOf(_id));
                if ( _id > 0 )
                    returnUri = NumbersContract.FactEntry.buildFactUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
