package com.jwelch.android.numb3rfacts;

/**
 * Created by jwelch on 6/30/15.
 */
public interface FetchFactsListener<T> {

    public void onTaskComplete(T result);
}
