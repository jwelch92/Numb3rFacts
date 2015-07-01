package com.jwelch.android.numb3rfacts;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jwelch.android.numb3rfacts.models.BaseFact;
import com.jwelch.android.numb3rfacts.numbers_api.NumbersApiWrapper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by jwelch on 6/30/15.
 */


public class MathFragment extends Fragment {

    private final String LOG_TAG = MathFragment.class.getSimpleName();

    @Bind(R.id.fact_textview)
    TextView factText;

    @Bind(R.id.fact_edittext)
    EditText factEdit;

    @Bind(R.id.fetch_fact_button)
    Button fetchFact;

    private BaseFact mCurrentFact;
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static MathFragment newInstance() {
//        Bundle args = new Bundle();
//        args.putInt(ARG_PAGE, page);
        MathFragment fragment = new MathFragment();
//        fragment.setArguments(args);
        return fragment;
    }

    private void logger(String msg) {
        Log.d(LOG_TAG, msg);
    }

    @OnClick(R.id.fetch_fact_button)
    public void fetchAFact() {
        logger("Button pressed!!!");
        logger(factEdit.getText().toString());
        FetchFacts task = new FetchFacts(getActivity(), new FetchMathFactListener());
        String text = factEdit.getText().toString();
        if (!text.equals("")) {
            task.execute(text);
        } else {
            Toast.makeText(getActivity(), "Please enter a number", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mPage = getArguments().getInt(ARG_PAGE);
        logger("onCreate");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_math, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public class FetchMathFactListener implements FetchFactsListener<BaseFact> {

        @Override
        public void onTaskComplete(BaseFact baseFact) {
            mCurrentFact = baseFact;
            factText.setText(mCurrentFact.text);
        }
    }



}
