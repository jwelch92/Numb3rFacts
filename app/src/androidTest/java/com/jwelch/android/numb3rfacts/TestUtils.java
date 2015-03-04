package com.jwelch.android.numb3rfacts;

import android.content.ContentValues;
import android.database.Cursor;
import android.test.AndroidTestCase;

import com.jwelch.android.numb3rfacts.data.NumbersContract.FactEntry;

import java.util.Map;
import java.util.Set;

/**
 * Created by jwelch on 3/4/15.
 */
public class TestUtils extends AndroidTestCase {

    public static ContentValues createMathFact() {
        ContentValues ret = new ContentValues();
        ret.put(FactEntry.COLUMN_TEXT, "This is test text");
        ret.put(FactEntry.COLUMN_YEAR, (byte[]) null);
        ret.put(FactEntry.COLUMN_DATE, (String)null);
        ret.put(FactEntry.COLUMN_NUMBER, 10);
        ret.put(FactEntry.COLUMN_FOUND, "true");
        ret.put(FactEntry.COLUMN_TYPE, "math");
        return ret;
    }


    static void validateCursor(String error, Cursor valueCursor, ContentValues expectedValues) {
        assertTrue("Empty cursor returned. " + error, valueCursor.moveToFirst());
        validateCurrentRecord(error, valueCursor, expectedValues);
        valueCursor.close();
    }

    static void validateCurrentRecord(String error, Cursor valueCursor, ContentValues expectedValues) {
        Set<Map.Entry<String, Object>> valueSet = expectedValues.valueSet();
        for (Map.Entry<String, Object> entry : valueSet) {
            String columnName = entry.getKey();
            int idx = valueCursor.getColumnIndex(columnName);
            assertFalse("Column '" + columnName + "' not found. " + error, idx == -1);
            String expectedValue = entry.getValue().toString();
            assertEquals("Value '" + entry.getValue().toString() +
                    "' did not match the expected value '" +
                    expectedValue + "'. " + error, expectedValue, valueCursor.getString(idx));
        }

    }
}
