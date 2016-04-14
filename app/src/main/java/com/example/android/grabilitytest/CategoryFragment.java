package com.example.android.grabilitytest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Luis Adrian on 08/04/2016.
 */
public class CategoryFragment extends Fragment {

    private List<Category> categoryList;
    private RecyclerView mRecyclerView;
    private ProgressBar progressBar;
    private MyRecyclerAdapter adapter;

    public CategoryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);



        // Initialize recycler view
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        final String url = "https://itunes.apple.com/us/rss/topfreeapplications/limit=20/json";
        new AsyncHttpTask().execute(url);

        return rootView;
    }

    private void setupWindowAnimations() {
        Slide slide = new Slide();
        slide.setDuration(1000);
        getActivity().getWindow().setExitTransition(slide);
    }


    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        private String TAG = AsyncHttpTask.class.getSimpleName();

        @Override
        protected void onPreExecute() {
            progressBar.setIndeterminate(true);
        }

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                // 200 represents HTTP OK
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    parseResult(response.toString());
                    result = 1; // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {
            // Download complete. Let us update UI
            progressBar.setVisibility(View.GONE);

            if (result == 1) {
                adapter = new MyRecyclerAdapter(getActivity(), categoryList);
                mRecyclerView.setAdapter(adapter);
                setupWindowAnimations();
            } else {
                Toast.makeText(getActivity(), "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void parseResult(String result) {
        try {

            final String OWN_ENTRY = "entry";
            final String OWN_CATEGORY = "category";
            final String OWN_ATTRIBUTE = "attributes";
            final String OWN_ID = "im:id";
            final String OWN_NEWS = "term";

            JSONObject response = new JSONObject(result);
            JSONObject feeds = response.getJSONObject("feed");
            JSONArray entry = feeds.getJSONArray(OWN_ENTRY);

            categoryList = new ArrayList<>();
            Set<String> titles = new HashSet<String>();


            for (int i = 0; i < entry.length(); i++) {

                JSONObject forEntry = entry.optJSONObject(i);

                JSONObject categoryeObject = forEntry.getJSONObject(OWN_CATEGORY);
                JSONObject attributeObject = categoryeObject.getJSONObject(OWN_ATTRIBUTE);

                String id = attributeObject.getString(OWN_ID);
                String term = attributeObject.getString(OWN_NEWS);

                Category item = new Category();
                item.setId(id);
                item.setTerm(term);

                //Eliminate Duplicates
                    if( titles.add(item.getId())) {
                        categoryList.add( item );
                    }

                }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
