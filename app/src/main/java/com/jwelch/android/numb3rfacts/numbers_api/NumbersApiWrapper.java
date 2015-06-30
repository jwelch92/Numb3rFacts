package com.jwelch.android.numb3rfacts.numbers_api;

import com.jwelch.android.numb3rfacts.models.BaseFact;

import retrofit.RestAdapter;

/**
 * Created by jwelch on 3/2/15.
 */
public class NumbersApiWrapper {
    public static final String NUMBERS_ENDPOINT = "https://numbersapi.p.mashape.com";
    private static final String LOG_TAG = NumbersApiWrapper.class.getSimpleName();
    private static RestAdapter mRestAdapter = new RestAdapter.Builder().setEndpoint(NUMBERS_ENDPOINT).build();
    private static NumbersApi mNumApi = mRestAdapter.create(NumbersApi.class);
    private NumbersApiWrapper() {
    }

    public static BaseFact fetchDateFact(String day, String month) {
        return mNumApi.getDateFact(day, month);
    }

    public static BaseFact fetchMathFact(String number) {
        return mNumApi.getMathFact(number);
    }

    public static BaseFact fetchTriviaFact(String number) {
        return mNumApi.getTriviaFact(number);
    }

    public static BaseFact fetchYearFact(String year) {
        return mNumApi.getYearFact(year);
    }

}
