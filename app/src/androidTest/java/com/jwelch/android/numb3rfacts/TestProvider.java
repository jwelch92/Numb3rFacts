package com.jwelch.android.numb3rfacts;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

import com.jwelch.android.numb3rfacts.data.NumbersContract;

/**
 * Created by jwelch on 3/4/15.
 */
public class TestProvider extends AndroidTestCase {
    public static final String AUTHORITY = "com.jwelch.android.numb3rfacts.data";

    public void testInsert() {
        ContentValues contentValues = TestUtils.createMathFact();
        Uri r = mContext.getContentResolver().insert(NumbersContract.FactEntry.CONTENT_URI, contentValues);

        Cursor cursor = mContext.getContentResolver().query(NumbersContract.FactEntry.CONTENT_URI, null, null, null, null);
        if (cursor.moveToFirst()) {
//            TestUtils.validateCursor("Error validating fact cursor", cursor, contentValues);
            int col = cursor.getColumnIndex(NumbersContract.FactEntry.COLUMN_NUMBER);
            assertEquals(10, cursor.getInt(col));
            Log.v("Cursor", DatabaseUtils.dumpCursorToString(cursor));
        }
        long id = 2;
    }

    public void testTypeQuery() {
        ContentValues contentValues = TestUtils.createMathFact();
        Uri r = mContext.getContentResolver().insert(NumbersContract.FactEntry.CONTENT_URI, contentValues);

        Uri getTypeUri = NumbersContract.FactEntry.buildFactUriWithType("math");
        Cursor cursor = mContext.getContentResolver().query(getTypeUri, null, null, null, null);
        if (cursor.moveToFirst()) {
            int col = cursor.getColumnIndex(NumbersContract.FactEntry.COLUMN_TYPE);
            assertEquals("math", cursor.getString(col));
            Log.v("Cursor", DatabaseUtils.dumpCursorToString(cursor));

        }

    }
}
