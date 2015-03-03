package com.jwelch.android.numb3rfacts.numbers_api;

import com.jwelch.android.numb3rfacts.models.DateFact;
import com.jwelch.android.numb3rfacts.models.MathFact;
import com.jwelch.android.numb3rfacts.models.TriviaFact;
import com.jwelch.android.numb3rfacts.models.YearFact;

import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by jwelch on 3/2/15.
 */
public interface NumbersApi {

    @Headers({
            "accept: text/plain",
            "X-Mashape-Key: g3DN69akvYmshsxtUfXhPG6FrkdLp1M7HrIjsnKtQmswE7JhyX"
    })
    @GET("/{day}/{month}/date?json=true")
    DateFact getDateFact(@Path("day") int day, @Path("month") int month);

    @Headers({
            "accept: text/plain",
            "X-Mashape-Key: g3DN69akvYmshsxtUfXhPG6FrkdLp1M7HrIjsnKtQmswE7JhyX"
    })
    @GET("/{number}/math?json=true")
    MathFact getMathFact(@Path("number") int number);

    @Headers({
            "accept: text/plain",
            "X-Mashape-Key: g3DN69akvYmshsxtUfXhPG6FrkdLp1M7HrIjsnKtQmswE7JhyX"
    })
    @GET("/{number}/trivia?json=true&notfound=default")
    TriviaFact getTriviaFact(@Path("number") int number);

    @Headers({
            "accept: text/plain",
            "X-Mashape-Key: g3DN69akvYmshsxtUfXhPG6FrkdLp1M7HrIjsnKtQmswE7JhyX"
    })
    @GET("/{year}/year?json=true")
    YearFact getYearFact(@Path("year") int year);

}
