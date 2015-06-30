package com.jwelch.android.numb3rfacts;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jwelch.android.numb3rfacts.models.BaseFact;
import com.jwelch.android.numb3rfacts.numbers_api.NumbersApiWrapper;

import butterknife.Bind;
import butterknife.ButterKnife;
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

        private final String LOG_TAG = NumberFragment.class.getSimpleName();

        @Bind(R.id.fact_textview)
        TextView factText;

        @Bind(R.id.fact_edittext)
        EditText factEdit;

        @Bind(R.id.fetch_fact_button)
        Button fetchFact;

        private BaseFact mCurrentFact;

        public NumberFragment() {
        }

        public void logger(String msg) {
            Log.d(LOG_TAG, msg);
        }

        @OnClick(R.id.fetch_fact_button)
        public void fetchAFact() {
            logger("Button pressed!!!");
            logger(factEdit.getText().toString());
            FetchFacts task = new FetchFacts();
            task.execute(factEdit.getText().toString());
        }

        public void setData() {
            factText.setText(mCurrentFact.text);
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            logger("onCreate");

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ButterKnife.bind(this, rootView);
            return rootView;
        }

        private class FetchFacts extends AsyncTask<String, Void, BaseFact> {

            @Override
            protected BaseFact doInBackground(String... params) {
                BaseFact mathFact = NumbersApiWrapper.fetchMathFact(params[0]);
                logger(mathFact.toString());
                if (null != mathFact.text) {
                    return mathFact;
                }
                return null;
            }

            @Override
            protected void onPostExecute(BaseFact baseFact) {
                mCurrentFact = baseFact;
                setData();
            }
        }


    }
}
