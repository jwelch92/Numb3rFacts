package com.jwelch.android.numb3rfacts;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import com.jwelch.android.numb3rfacts.data.NumbersSQLiteOpenHelper;
import com.jwelch.android.numb3rfacts.models.MathFact;
import com.jwelch.android.numb3rfacts.numbers_api.NumbersApiWrapper;

import java.util.ArrayList;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by jwelch on 3/3/15.
 */
public class TestDB extends AndroidTestCase {

    public void testInsert() {
        MathFact mathFact = new MathFact(1L, "Test text", 100, true, "math");
        SQLiteDatabase db = new NumbersSQLiteOpenHelper(mContext).getWritableDatabase();
        assertNotNull(db);
        long id = cupboard().withDatabase(db).put(mathFact);
        assertEquals(1L, id);

        MathFact newMathFact = cupboard().withDatabase(db).get(MathFact.class, 1L);
        assertNotNull(newMathFact);
        assertEquals(1L, newMathFact._id);
        assertEquals(100, newMathFact.number);
    }

    public void testBulkInsert() {
        ArrayList<MathFact> mathFactList = new ArrayList<>();
        int start;
        int end = 10;
        for (start = 0; start < end; start++) {
            mathFactList.add(start, NumbersApiWrapper.getMathFact(start));
        }

        for (MathFact fact : mathFactList) {
            Log.v("testBulkInsert", fact.text);
        }
    }

    public <E> void insertGeneric(E fact) {
        SQLiteDatabase db = new NumbersSQLiteOpenHelper(mContext).getWritableDatabase();
        long id = cupboard().withDatabase(db).put(fact);
        Log.v("insertGeneric", String.valueOf(id));

        MathFact mf = cupboard().withDatabase(db).get(MathFact.class, id);
        Log.v("insertGeneric", mf.text + mf.type + String.valueOf(mf.number));

    }

    public void testInsertGeneric() {
        int number = 5;
        insertGeneric(NumbersApiWrapper.getMathFact(number));

    }
}
