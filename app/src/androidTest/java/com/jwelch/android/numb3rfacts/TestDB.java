package com.jwelch.android.numb3rfacts;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.jwelch.android.numb3rfacts.data.NumbersSQLiteOpenHelper;
import com.jwelch.android.numb3rfacts.models.BaseFact;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by jwelch on 3/3/15.
 */
public class TestDB extends AndroidTestCase {

    public void testInsert() {
        BaseFact mathFact = new BaseFact(1L, "Test text", 100, true, "math");
        SQLiteDatabase db = new NumbersSQLiteOpenHelper(mContext).getWritableDatabase();
        assertNotNull(db);
        long id = cupboard().withDatabase(db).put(mathFact);
        assertEquals(1L, id);

        BaseFact newMathFact = cupboard().withDatabase(db).get(BaseFact.class, 1L);
        assertNotNull(newMathFact);
        assertEquals(1L, newMathFact._id);
        assertEquals(100, newMathFact.number);
    }

    public void testBulkInsert() {
//        ArrayList<MathFact> mathFactList = new ArrayList<>();
        int start;
        int end = 10;
//        for (start = 0; start < end; start++) {
//            mathFactList.add(start, NumbersApiWrapper.getMathFact(start));
//        }

//        for (MathFact fact : mathFactList) {
//            Log.v("testBulkInsert", fact.text);
        }
//    }


    public void testDateFactInsert() {
        BaseFact df = new BaseFact(5L, "Test date", 1975, 2, true, "date");
        SQLiteDatabase db = new NumbersSQLiteOpenHelper(mContext).getWritableDatabase();

        long _id = cupboard().withDatabase(db).put(df);
        assertEquals(5L, _id);

        BaseFact bf = cupboard().withDatabase(db).get(BaseFact.class, _id);
        assertEquals(2, bf.number);
        assertEquals("date", bf.type);
        assertEquals(1975, bf.year);
    }

    public void testYearFactInsert() {
        BaseFact df = new BaseFact(10L, "Test year fact", "April 20", 1992, true, "year");
        SQLiteDatabase db = new NumbersSQLiteOpenHelper(mContext).getWritableDatabase();

        long _id = cupboard().withDatabase(db).put(df);
        assertEquals(10L, _id);

        BaseFact bf = cupboard().withDatabase(db).get(BaseFact.class, _id);
        assertEquals(1992, bf.number);
        assertEquals("year", bf.type);
        assertEquals("April 20", bf.date);

    }


}
