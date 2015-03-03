package com.jwelch.android.numb3rfacts.models;

/**
 * Created by jwelch on 3/2/15.
 */
public class TriviaFact {

    public long _id;

    public String text;

    public int number;

    public boolean found;

    public String type;


    public TriviaFact(long _id, String text, int number, boolean found, String type) {
        this._id = _id;
        this.text = text;
        this.number = number;
        this.found = found;
        this.type = type;
    }
}
