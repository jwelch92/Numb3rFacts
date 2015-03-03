package com.jwelch.android.numb3rfacts.models;

/**
 * Created by jwelch on 3/3/15.
 */
public class BaseFact {

    public long _id;

    public String text;

    public int year;

    public int number;

    public boolean found;

    public String type;

    public BaseFact(long _id, String text, int number, boolean found, String type) {
        this._id = _id;
        this.text = text;
        this.number = number;
        this.found = found;
        this.type = type;
    }

    public BaseFact() {
    }
}
