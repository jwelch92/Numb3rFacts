package com.jwelch.android.numb3rfacts.numbers_api;

import com.jwelch.android.numb3rfacts.models.BaseFact;

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
    BaseFact getDateFact(@Path("day") String day, @Path("month") String month);

    @Headers({
            "accept: text/plain",
            "X-Mashape-Key: g3DN69akvYmshsxtUfXhPG6FrkdLp1M7HrIjsnKtQmswE7JhyX"
    })
    @GET("/{number}/math?json=true")
    BaseFact getMathFact(@Path("number") String number);

    @Headers({
            "accept: text/plain",
            "X-Mashape-Key: g3DN69akvYmshsxtUfXhPG6FrkdLp1M7HrIjsnKtQmswE7JhyX"
    })
    @GET("/{number}/trivia?json=true&notfound=default")
    BaseFact getTriviaFact(@Path("number") String number);

    @Headers({
            "accept: text/plain",
            "X-Mashape-Key: g3DN69akvYmshsxtUfXhPG6FrkdLp1M7HrIjsnKtQmswE7JhyX"
    })
    @GET("/{year}/year?json=true")
    BaseFact getYearFact(@Path("year") String year);

}
