package com.jwelch.android.numb3rfacts;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jwelch.android.numb3rfacts.models.BaseFact;
import com.jwelch.android.numb3rfacts.numbers_api.NumbersApiWrapper;

import java.util.concurrent.ExecutionException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new NumberFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class NumberFragment extends Fragment {

        private AsyncTask task = new FetchFacts();
        private static final String LOG_TAG = NumberFragment.class.getSimpleName();

        public static void logger(String msg) {
            Log.d(LOG_TAG, msg);
        }

        @InjectView(R.id.fact_textview)
        TextView factText;

        @InjectView(R.id.fact_edittext)
        EditText factEdit;

        @InjectView(R.id.fetch_fact_button)
        Button fetchFact;

        @OnClick(R.id.fetch_fact_button)
        public void fetchAFact()  {
            logger("Button pressed!!!");
            task.execute(factEdit.getText().toString());
        }


        public void setData(String text) {
            factEdit.setText(text);
        }

        public NumberFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            ButterKnife.inject(getActivity());

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        private class FetchFacts extends AsyncTask<Integer, Void, BaseFact> {

            @Override
            protected BaseFact doInBackground(Integer... params) {
                BaseFact mathFact =  NumbersApiWrapper.fetchMathFact(params[0]);
                logger(mathFact.toString());
                if (null != mathFact.text) {
                    return mathFact;
                }
                return null;
            }

            @Override
            protected void onPostExecute(BaseFact baseFact) {
                setData(baseFact.text);
            }
        }


    }
}
