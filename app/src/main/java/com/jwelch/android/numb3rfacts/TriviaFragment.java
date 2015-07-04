package com.jwelch.android.numb3rfacts;

/**
 * Created by jwelch on 7/3/15.
 */

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
import com.jwelch.android.numb3rfacts.models.FactArgs;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by jwelch on 7/1/15.
 */
public class TriviaFragment extends Fragment {

    private final String LOG_TAG = TriviaFragment.class.getSimpleName();
    public BaseFact mCurrentFact;
    @Bind(R.id.fact_trivia_textview)
    TextView factText;
    @Bind(R.id.fact_trivia_edittext)
    EditText factEdit;
    @Bind(R.id.fetch_fact_trivia_button)
    Button fetchFact;

    public static TriviaFragment newInstance() {
        TriviaFragment fragment = new TriviaFragment();
        return fragment;
    }

    private void logger(String msg) {
        Log.d(LOG_TAG, msg);
    }

    @OnClick(R.id.fetch_fact_trivia_button)
    public void fetchAFact() {
        logger("Button pressed!!!");
        logger(factEdit.getText().toString());
        FetchFacts task = new FetchFacts(getActivity(), new FetchTriviaFactListener());
        String text = factEdit.getText().toString();

        FactArgs args = new FactArgs("trivia", text, null, null);

        if (!text.equals("")) {
            task.execute(args);
        } else {
            Toast.makeText(getActivity(), "Please enter a number", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Icepick.restoreInstanceState(this, savedInstanceState);

    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        Icepick.saveInstanceState(this, outState);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trivia, container, false);
        ButterKnife.bind(this, rootView);
        if (mCurrentFact != null) {
            factText.setText(mCurrentFact.text);
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public class FetchTriviaFactListener implements FetchFactsListener<BaseFact> {

        @Override
        public void onTaskComplete(BaseFact baseFact) {
            mCurrentFact = baseFact;
            factText.setText(mCurrentFact.text);
        }
    }


}

