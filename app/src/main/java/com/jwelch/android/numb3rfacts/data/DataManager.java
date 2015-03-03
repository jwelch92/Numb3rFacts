package com.jwelch.android.numb3rfacts.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jwelch.android.numb3rfacts.models.DateFact;
import com.jwelch.android.numb3rfacts.models.MathFact;
import com.jwelch.android.numb3rfacts.models.TriviaFact;
import com.jwelch.android.numb3rfacts.models.YearFact;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by jwelch on 3/3/15.
 */
public class DataManager {
    private static final String LOG_TAG = DataManager.class.getSimpleName();

    public DataManager(Context mContext) {
        this.mContext = mContext;
    }

    private void logger(String message) {
        Log.v(LOG_TAG, message);
    }
    private Context mContext;
    private SQLiteDatabase mDatabase = new NumbersSQLiteOpenHelper(mContext).getWritableDatabase();


    public DateFact getDateFact(long id) {
        return cupboard().withDatabase(mDatabase).get(DateFact.class, id);
    }

    public MathFact getMathFact(long id) {
        return cupboard().withDatabase(mDatabase).get(MathFact.class, id);
    }

    public TriviaFact getTriviaFact(long id) {
        return cupboard().withDatabase(mDatabase).get(TriviaFact.class, id);
    }

    public YearFact getYearFact(long id) {
        return cupboard().withDatabase(mDatabase).get(YearFact.class, id);
    }
}
