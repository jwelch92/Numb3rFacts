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

    // for trivia and math fact
//    public BaseFact(long _id, String text, int number, boolean found, String type) {
//        this._id = _id;
//        this.text = text;
//        this.number = number;
//        this.found = found;
//        this.type = type;
//    }
//
//    // for date
//    public BaseFact(long _id, String text, int year, int number, boolean found, String type) {
//        this._id = _id;
//        this.text = text;
//        this.year = year;
//        this.number = number;
//        this.found = found;
//        this.type = type;
//    }
//
//    // for year
//    public BaseFact(long _id, String text, String date, int number, boolean found, String type) {
//        this._id = _id;
//        this.text = text;
//        this.date = date;
//        this.number = number;
//        this.found = found;
//        this.type = type;
//    }

//    @Override
//    public String toString() {
//        String dash = " - ";
//        return String.valueOf(this._id) + dash + this.text + dash
//                + String.valueOf(this.number) + dash + this.type;
//
//    }
}
