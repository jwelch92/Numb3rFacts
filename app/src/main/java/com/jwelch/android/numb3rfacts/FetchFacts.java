package com.jwelch.android.numb3rfacts;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.jwelch.android.numb3rfacts.models.BaseFact;
import com.jwelch.android.numb3rfacts.models.FactArgs;
import com.jwelch.android.numb3rfacts.numbers_api.NumbersApiWrapper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jwelch on 6/30/15.
 */
public class FetchFacts extends AsyncTask<FactArgs, Void, BaseFact> {

    private Context mContext;
    private FetchFactsListener<BaseFact> mListener;

    public FetchFacts(Context context, FetchFactsListener<BaseFact> listener) {
        this.mContext = context;
        this.mListener = listener;
    }

    @Override
    protected BaseFact doInBackground(FactArgs... params) {
        BaseFact fact = null;
        FactArgs args = params[0];
        String type = args.getType();
        if (type.equals("math")) {
            fact = NumbersApiWrapper.fetchMathFact(args.getNumber());
        } else if (type.equals("date")) {
            fact = NumbersApiWrapper.fetchDateFact(args.getDates()[0], args.getDates()[1]);
        } else if (type.equals("year")) {
            fact = NumbersApiWrapper.fetchYearFact(args.getYear());
        }

        return fact;
    }

    @Override
    protected void onPostExecute(BaseFact baseFact) {
        super.onPostExecute(baseFact);
        mListener.onTaskComplete(baseFact);
    }
}
