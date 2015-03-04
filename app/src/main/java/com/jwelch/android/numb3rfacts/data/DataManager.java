package com.jwelch.android.numb3rfacts.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jwelch.android.numb3rfacts.models.BaseFact;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by jwelch on 3/3/15.
 */
public class DataManager {
    private static final String LOG_TAG = DataManager.class.getSimpleName();

    public DataManager(Context context) {
        mDatabase = new NumbersDBHelper(context).getWritableDatabase();
    }

    private void logger(String message) {
        Log.v(LOG_TAG, message);
    }
//    private Context mContext;
    private SQLiteDatabase mDatabase;

    public BaseFact getFactById(long id) {
        return cupboard().withDatabase(mDatabase).get(BaseFact.class, id);
    }

    public BaseFact getFactByNumber(int number) {
        return cupboard().withDatabase(mDatabase).query(BaseFact.class).withSelection("number = ?", String.valueOf(number)).get();

    }

//    public ArrayList<BaseFact> getFactsByType(String type) {
//        List<BaseFact> list =  cupboard().withDatabase(mDatabase).query(BaseFact.class).withSelection("type = ?", type).list();
//        return new ArrayList<>(list);
//
//    }

    public void deleteFactById(long id) {
        cupboard().withDatabase(mDatabase).delete(BaseFact.class, id);
    }

    public long insertFact(BaseFact fact) {
        return cupboard().withDatabase(mDatabase).put(fact);
    }

//    public void bulkInsert(ArrayList<BaseFact> baseFacts) {
//        for (BaseFact fact : baseFacts) {
//            cupboard().withDatabase(mDatabase).put(fact);
//        }
//    }

    public void updateById(long id) {
    }

    public void deleteAllTables() {
        cupboard().withDatabase(mDatabase).dropAllTables();
    }
}
