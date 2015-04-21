package com.jwelch.android.numb3rfacts.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
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
    static final int FACT_WTH_TYPE = 101;
    static final int FACT_WITH_TYPE_AND_NUMBER = 102;

    private static final SQLiteQueryBuilder sQueryBuilder;

    static {
        sQueryBuilder = new SQLiteQueryBuilder();
        sQueryBuilder.setTables(NumbersContract.FactEntry.TABLE_NAME);
    }

    private static final String sFactTypeSelection = NumbersContract.FactEntry.TABLE_NAME + "."
            + NumbersContract.FactEntry.COLUMN_TYPE + " = ? ";

    private static final String sFactTypeAndNumberSelection = NumbersContract.FactEntry.TABLE_NAME + "."
            + NumbersContract.FactEntry.COLUMN_TYPE + " = ? AND" + NumbersContract.FactEntry.COLUMN_NUMBER
            + " = ? ";

    static UriMatcher buildUriMatcher() {
        // 1) The code passed into the constructor represents the code to return for the root
        // URI.  It's common to use NO_MATCH as the code for this case. Add the constructor below.
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = NumbersContract.CONTENT_AUTHORITY;
        // 2) Use the addURI function to match each of the types.  Use the constants from
        // WeatherContract to help define the types to the UriMatcher.
        uriMatcher.addURI(authority, NumbersContract.PATH_FACT, FACT);
        uriMatcher.addURI(authority, NumbersContract.PATH_FACT + "/*", FACT_WTH_TYPE);
        uriMatcher.addURI(authority, NumbersContract.PATH_FACT + "/*/#", FACT_WITH_TYPE_AND_NUMBER);
        // 3) Return the new matcher!
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new NumbersDBHelper(getContext());
        return true;
    }

    private Cursor getFactByType(Uri uri, String[] projection, String sortOrder) {
        String type = NumbersContract.FactEntry.getTypeFromUri(uri);
        String[] selectionArgs = new String[]{type};
        String selection = sFactTypeSelection;
        return sQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
    }

    private Cursor getFactByTypeAndNumber(Uri uri, String[] projection, String sortOrder) {
        String type = NumbersContract.FactEntry.getTypeFromUri(uri);
        int number = NumbersContract.FactEntry.getNumberFromUri(uri);
        String[] selectionArgs = new String[]{type, String.valueOf(number)};
        String selection = sFactTypeAndNumberSelection;
        return sQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
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
                break;
            case FACT_WTH_TYPE:
                retCursor = getFactByType(uri, projection, sortOrder);
                break;
            case FACT_WITH_TYPE_AND_NUMBER:
                retCursor  =getFactByTypeAndNumber(uri, projection, sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        return retCursor;
    }

    @Override
    public String getType(Uri uri) {
        switch(sUriMatcher.match(uri)) {
            case FACT:
                return NumbersContract.FactEntry.CONTENT_TYPE;
            case FACT_WTH_TYPE:
                return NumbersContract.FactEntry.CONTENT_TYPE;
            case FACT_WITH_TYPE_AND_NUMBER:
                return NumbersContract.FactEntry.CONTENT_ITEM_TYPE;
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
//            case FACT_WTH_TYPE: {
//                long _id = db.insert(NumbersContract.FactEntry.TABLE_NAME, null, values);
//                Log.v(TAG, "Row id is: " + String.valueOf(_id));
//                if ( _id > 0 )
//
//                    returnUri = NumbersContract.FactEntry.buildFactUriWithType(NumbersContract.FactEntry.getTypeFromUri(uri));
//                else
//                    throw new android.database.SQLException("Failed to insert row into " + uri);
//                break;
//            }
//            case FACT_WITH_TYPE_AND_NUMBER: {
//                long _id = db.insert(NumbersContract.FactEntry.TABLE_NAME, null, values);
//                Log.v(TAG, "Row id is: " + String.valueOf(_id));
//                if ( _id > 0 )
//                    returnUri = NumbersContract.FactEntry.b;
//                else
//                    throw new android.database.SQLException("Failed to insert row into " + uri);
//                break;
//            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        // Student: Use the uriMatcher to match the WEATHER and LOCATION URI's we are going to
        // handle.  If it doesn't match these, throw an UnsupportedOperationException.
        int rowsDeleted;
        if (null == selection) selection = "1";
        switch (sUriMatcher.match(uri)) {
            case FACT: {
                rowsDeleted = db.delete(NumbersContract.FactEntry.TABLE_NAME, selection, selectionArgs);
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        // Student: A null value deletes all rows.  In my implementation of this, I only notified
        // the uri listeners (using the content resolver) if the rowsDeleted != 0 or the selection
        // is null.
        // Oh, and you should notify the listeners here.
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        // Student: return the actual rows deleted
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        // Student: Use the uriMatcher to match the WEATHER and LOCATION URI's we are going to
        // handle.  If it doesn't match these, throw an UnsupportedOperationException.
        int rowsUpdated;
        switch (sUriMatcher.match(uri)) {
            case FACT: {
                rowsUpdated = db.update(NumbersContract.FactEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }
}
