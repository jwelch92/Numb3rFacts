package com.jwelch.android.numb3rfacts.models;

/**
 * Created by jwelch on 7/1/15.
 */
public class FactArgs {

    private String type;
    private String number;
    private String[] dates;
    private String year;


    public FactArgs(String type, String number, String[] dates, String year) {
        this.type = type;
        this.number = number;
        this.dates = dates;
        this.year = year;
    }

    public FactArgs(String type, String number) {
        this(type, number, null, null);
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String[] getDates() {
        return dates;
    }

    public void setDates(String[] dates) {
        this.dates = dates;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
