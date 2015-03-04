package com.jwelch.android.numb3rfacts;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

import com.jwelch.android.numb3rfacts.data.NumberProvider.Fact;

/**
 * Created by jwelch on 3/4/15.
 */
public class TestProvider extends AndroidTestCase {
    public static final String AUTHORITY = "com.jwelch.android.numb3rfacts.data";

    public void testInsert() {
        Uri uri = Uri.parse(AUTHORITY + "/facts");
        ContentValues contentValues = new ContentValues();
        contentValues.put(Fact.KEY_TEXT, "This test text for a fact");
        contentValues.put(Fact.KEY_NUMBER, 5);
        contentValues.put(Fact.KEY_FOUND, "true");
        contentValues.put(Fact.KEY_TYPE, "math");
        mContext.getContentResolver().insert(uri, contentValues);



        Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
        if (cursor.moveToFirst()) {
            int col = cursor.getColumnIndex(Fact.KEY_TEXT);
            String t = cursor.getString(col);
            Log.v("TestProvider", t);
            assertEquals("This test text for a fact", t);
        }
    }


}
