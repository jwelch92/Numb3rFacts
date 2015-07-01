package com.jwelch.android.numb3rfacts;

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
 * Created by jwelch on 6/30/15.
 */
public class DateFragment extends Fragment {

    private final String LOG_TAG = DateFragment.class.getSimpleName();

    @Bind(R.id.fact_textview)
    TextView factText;

    @Bind(R.id.fact_date_edittext_1)
    EditText factDateEdit1;

    @Bind(R.id.fact_date_edittext_2)
    EditText factDateEdit2;

    @Bind(R.id.fetch_fact_button)
    Button fetchFact;

    private BaseFact mCurrentFact;
    
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static DateFragment newInstance() {
//        Bundle args = new Bundle();
//        args.putInt(ARG_PAGE, page);
        DateFragment fragment = new DateFragment();
//        fragment.setArguments(args);
        return fragment;
    }

    private void logger(String msg) {
        Log.d(LOG_TAG, msg);
    }

    @OnClick(R.id.fetch_fact_button)
    public void fetchAFact() {

        FetchFacts task = new FetchFacts(getActivity(), new FetchDateFactListener());
        String text1 = factDateEdit1.getText().toString();
        String text2 = factDateEdit2.getText().toString();
        String[] dates = new String[] {text1, text2};
        FactArgs args = new FactArgs("math", null, dates, null);

        if (!text1.equals("") && !text2.equals("")) {
            task.execute(args);
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

    public class FetchDateFactListener implements FetchFactsListener<BaseFact> {

        @Override
        public void onTaskComplete(BaseFact baseFact) {
            mCurrentFact = baseFact;
            factText.setText(mCurrentFact.text);
        }
    }



}
