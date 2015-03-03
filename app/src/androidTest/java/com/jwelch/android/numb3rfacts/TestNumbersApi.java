package com.jwelch.android.numb3rfacts;

import android.test.AndroidTestCase;
import android.util.Log;

import com.jwelch.android.numb3rfacts.models.DateFact;
import com.jwelch.android.numb3rfacts.models.MathFact;
import com.jwelch.android.numb3rfacts.models.TriviaFact;
import com.jwelch.android.numb3rfacts.models.YearFact;
import com.jwelch.android.numb3rfacts.numbers_api.NumbersApi;
import com.jwelch.android.numb3rfacts.numbers_api.NumbersApiWrapper;

import retrofit.RestAdapter;

/**
 * Created by jwelch on 3/2/15.
 */
public class TestNumbersApi extends AndroidTestCase {
    public static final String LOG_TAG = TestNumbersApi.class.getSimpleName();
    public static final String NUMBERS_ENDPOINT = "https://numbersapi.p.mashape.com";

    public void testGetDateFact() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(NUMBERS_ENDPOINT).build();

        NumbersApi service = restAdapter.create(NumbersApi.class);

        DateFact dateFact = service.getDateFact(6, 21);
        assertNotNull(dateFact);

        logger(dateFact.text);
        logger(dateFact.type);
        assertEquals("date", dateFact.type);
    }


    public void testGetMathFact() {
        int test = 121;
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(NUMBERS_ENDPOINT).build();

        NumbersApi service = restAdapter.create(NumbersApi.class);
        MathFact mathFact = service.getMathFact(test);
        assertNotNull(mathFact);

        logger(mathFact.text);
        logger(mathFact.type);
        logger(String.valueOf(mathFact.number));

        assertEquals(test, mathFact.number);
    }


    public void testGetTriviaFact() {
        int test = 100;
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(NUMBERS_ENDPOINT).build();

        NumbersApi service = restAdapter.create(NumbersApi.class);
        TriviaFact triviaFact = service.getTriviaFact(test);
        assertNotNull(triviaFact);

        logger(triviaFact.text);
        logger(triviaFact.type);
        logger(String.valueOf(triviaFact.number));
        assertEquals(test, triviaFact.number);
    }


    public void testGetYearFact() {
        int year = 1992;
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(NUMBERS_ENDPOINT).build();

        NumbersApi service = restAdapter.create(NumbersApi.class);
        YearFact yearFact = service.getYearFact(year);
        assertNotNull(yearFact);

        logger(yearFact.text);
        logger(yearFact.type);
        logger(String.valueOf(yearFact.number));
        assertEquals(year, yearFact.number);
    }


    public void logger(String message) {
        Log.v(LOG_TAG, message);
    }


    public void testWrapper() {
        DateFact dateFact = NumbersApiWrapper.fetchDateFact(2, 2);
        assertNotNull(dateFact);
        logger(dateFact.text);
        assertEquals("date", dateFact.type);
    }

}
