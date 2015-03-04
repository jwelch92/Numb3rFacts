package com.jwelch.android.numb3rfacts.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jwelch.android.numb3rfacts.data.NumbersContract.FactEntry;


public class NumbersDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "numberfacts.db";
    private static final int DATABASE_VERSION = 1;


    public NumbersDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_FACT_TABLE = "CREATE TABLE " + FactEntry.TABLE_NAME + " (" +
                FactEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FactEntry.COLUMN_TEXT + " TEXT NOT NULL, " +
                FactEntry.COLUMN_YEAR+ " INTEGER, " +
                FactEntry.COLUMN_DATE + " TEXT, " +
                FactEntry.COLUMN_NUMBER + " INTEGER NOT NULL," +
                FactEntry.COLUMN_FOUND + " TEXT NOT NULL, " +
                FactEntry.COLUMN_TYPE + " TEXT NOT NULL " +
                ");";

        db.execSQL(SQL_CREATE_FACT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FactEntry.TABLE_NAME);
        onCreate(db);
    }
}
