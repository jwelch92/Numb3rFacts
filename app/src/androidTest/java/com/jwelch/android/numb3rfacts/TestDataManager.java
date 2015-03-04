package com.jwelch.android.numb3rfacts;

import android.test.AndroidTestCase;
import android.util.Log;

import com.jwelch.android.numb3rfacts.data.DataManager;
import com.jwelch.android.numb3rfacts.data.NumbersSQLiteOpenHelper;
import com.jwelch.android.numb3rfacts.models.BaseFact;
import com.jwelch.android.numb3rfacts.numbers_api.NumbersApiWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jwelch on 3/3/15.
 */
public class TestDataManager extends AndroidTestCase{

    public DataManager getDataManager() {
        return new DataManager(mContext);
    }

    public void testGetFactById() {
        DataManager dataManager = getDataManager();
        long id = dataManager.insertFact(StubData.datefact);
        assertEquals(StubData.datefact._id, id);
        BaseFact test = dataManager.getFactById(id);
//        assertEquals(StubData.datefact, test);
        Log.v("testGetFactByID", test.toString());
        assertEquals(StubData.datefact._id, test._id);
        assertEquals(StubData.datefact.number, test.number);
        assertEquals(StubData.datefact.text, test.text);
    }


    public void testGetFactByNumber() {
        DataManager dataManager = getDataManager();
        long id = dataManager.insertFact(StubData.mathfact);
        assertEquals(StubData.mathfact._id, id);
        BaseFact test = dataManager.getFactByNumber(StubData.mathfact.number);
        assertNotNull(test);
        Log.v("testGetFactByNumber", test.toString());

    }

    public void testGetFactsByType() {
        int start = 0;
        int stop = 10;
        DataManager dataManager = getDataManager();
        for (start = 0; start< stop; start++) {
            Log.v("num", String.valueOf(start));
            BaseFact x = NumbersApiWrapper.fetchMathFact(start);
            Log.v("fact", x.toString());
            long id = dataManager.insertFact(x);
            Log.v("ID", "id inserted: " + String.valueOf(id));
        }

        ArrayList<BaseFact> facts = dataManager.getFactsByType("math");
        for (BaseFact f : facts) {
            Log.v("testFactsByType", f.toString());
        }
    }
}
