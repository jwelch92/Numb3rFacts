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
        BaseFact fact;
//        logger(mathFact.toString());
        String type = params[0];
        switch (type) {
            case "math":
                fact = NumbersApiWrapper.fetchMathFact(params[1]);
            case "date":
                fact = NumbersApiWrapper.fetchDateFact(params[1], params[2]);

        }

        return null;
    }

    @Override
    protected void onPostExecute(BaseFact baseFact) {
        super.onPostExecute(baseFact);
        mListener.onTaskComplete(baseFact);
    }
}
