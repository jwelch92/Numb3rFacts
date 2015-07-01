package com.jwelch.android.numb3rfacts;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.jwelch.android.numb3rfacts.models.BaseFact;
import com.jwelch.android.numb3rfacts.numbers_api.NumbersApiWrapper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jwelch on 6/30/15.
 */
public class FetchFacts extends AsyncTask<HashMap<String, ArrayList<String>>, Void, BaseFact> {

    private Context mContext;
    private FetchFactsListener<BaseFact> mListener;

    public FetchFacts(Context context, FetchFactsListener<BaseFact> listener) {
        this.mContext = context;
        this.mListener = listener;
    }

    @Override
    protected BaseFact doInBackground(HashMap<String, ArrayList<String>>... params) {
        BaseFact fact = null;
//        logger(mathFact.toString());
        HashMap<String, ArrayList<String>> args = params[0];
        ArrayList<String> numArgs = args.get("number");
        ArrayList<String> type = args.get("type");
        Log.d("FetchFacts", type.toString());
        Log.d("FetchFacts", type.get(0));
        if (type.equals("math")) {
            fact = NumbersApiWrapper.fetchMathFact(numArgs.get(0));
        } else if (type.equals("date")) {
            fact = NumbersApiWrapper.fetchDateFact(numArgs.get(0), numArgs.get(1));
        }

        return fact;
    }

    @Override
    protected void onPostExecute(BaseFact baseFact) {
        super.onPostExecute(baseFact);
        mListener.onTaskComplete(baseFact);
    }
}
