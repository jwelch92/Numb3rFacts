package com.jwelch.android.numb3rfacts.models;

/**
 * Created by jwelch on 3/3/15.
 */
public class BaseFact {

    public String text;

    public int year;

    public String date;

    public int number;

    public boolean found;

    public String type;

    // default empty
    public BaseFact() {
    }

    @Override
    public String toString() {
        return "Fact: " + this.text + " - " + this.number + " - " + this.type + " - ";
    }
}
