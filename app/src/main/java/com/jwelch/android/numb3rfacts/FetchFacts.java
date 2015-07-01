package com.jwelch.android.numb3rfacts;

import android.content.Context;
import android.os.AsyncTask;

import com.jwelch.android.numb3rfacts.models.BaseFact;
import com.jwelch.android.numb3rfacts.numbers_api.NumbersApiWrapper;

/**
 * Created by jwelch on 6/30/15.
 */
public class FetchFacts extends AsyncTask<String, Void, BaseFact> {

    private Context mContext;
    private FetchFactsListener<BaseFact> mListener;

    public FetchFacts(Context context, FetchFactsListener<BaseFact> listener) {
        this.mContext = context;
        this.mListener = listener;
    }

    @Override
    protected BaseFact doInBackground(String... params) {
        BaseFact mathFact = NumbersApiWrapper.fetchMathFact(params[0]);
//        logger(mathFact.toString());
        if (null != mathFact.text) {
            return mathFact;
        }
        return null;
    }

    @Override
    protected void onPostExecute(BaseFact baseFact) {
        super.onPostExecute(baseFact);
        mListener.onTaskComplete(baseFact);
    }
}
