package com.jwelch.android.numb3rfacts.models;

/**
 * Created by jwelch on 3/2/15.
 */
public class DateFact {

    public long _id;

    public String text;

    public int year;

    public int number;

    public boolean found;

    public String type;

    public DateFact() {

    }

    public DateFact(long _id, String text, int year, int number, boolean found, String type) {
        this._id = _id;
        this.text = text;
        this.year = year;
        this.number = number;
        this.found = found;
        this.type = type;
    }
}
