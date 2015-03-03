package com.jwelch.android.numb3rfacts.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jwelch.android.numb3rfacts.models.DateFact;
import com.jwelch.android.numb3rfacts.models.MathFact;
import com.jwelch.android.numb3rfacts.models.TriviaFact;
import com.jwelch.android.numb3rfacts.models.YearFact;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by jwelch on 3/2/15.
 */
public class NumbersSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "numberfacts.db";
    private static final int DATABASE_VERSION = 1;

    static {
        cupboard().register(DateFact.class);
        cupboard().register(MathFact.class);
        cupboard().register(TriviaFact.class);
        cupboard().register(YearFact.class);
    }


    public NumbersSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        cupboard().withDatabase(db).upgradeTables();
    }
}
