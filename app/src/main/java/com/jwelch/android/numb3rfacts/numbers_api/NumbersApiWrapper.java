package com.jwelch.android.numb3rfacts.numbers_api;

import com.jwelch.android.numb3rfacts.models.BaseFact;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit.RestAdapter;

/**
 * Created by jwelch on 3/2/15.
 */
public class  NumbersApiWrapper {
    private static final String LOG_TAG = NumbersApiWrapper.class.getSimpleName();
    public static final String NUMBERS_ENDPOINT = "https://numbersapi.p.mashape.com";

    private NumbersApiWrapper() {
    }

    private static RestAdapter mRestAdapter = new RestAdapter.Builder().setEndpoint(NUMBERS_ENDPOINT).build();
    private static NumbersApi mNumApi = mRestAdapter.create(NumbersApi.class);

    public static BaseFact fetchDateFact(int day, int month) {
        return mNumApi.getDateFact(day, month);
    }

    public static BaseFact fetchMathFact(int number) {
        return mNumApi.getMathFact(number);
    }

    public static BaseFact fetchTriviaFact(int number) {
        return mNumApi.getTriviaFact(number);
    }

    public static BaseFact fetchYearFact(int year) {
        return mNumApi.getYearFact(year);
    }

}
