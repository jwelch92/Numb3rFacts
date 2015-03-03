package com.jwelch.android.numb3rfacts;

import com.jwelch.android.numb3rfacts.models.BaseFact;

/**
 * Created by jwelch on 3/3/15.
 */
public class StubData {

    public static final BaseFact datefact = new BaseFact(100L, "Datefact text", 1975, 15, true, "date");
    public static final BaseFact mathfact = new BaseFact(101L, "Mathfact text", 11, true, "math");
    public static final BaseFact triviafact = new BaseFact(102L, "Trivia fact text", 12, true, "trivia");
    public static final BaseFact yearfact = new BaseFact(103L, "Yearfact text", "April 20", 1990, true, "year");
}
