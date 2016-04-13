package com.example.android.grabilitytest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import java.util.List;
import java.util.Set;

/**
 * Created by Luis Adrian on 13/04/2016.
 */
public class AppsFragment extends Fragment {

    private List<Apps> appList;
    private RecyclerView mRecyclerView;
    private ProgressBar progressBar;
    private MyRecyclerAdapterApps adapter;
    private String idCategory;
    private String nameCategory;

    public AppsFragment() {
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

        View rootView = inflater.inflate(R.layout.fragment_apps, container, false);

        Bundle extras = getActivity().getIntent().getExtras();
        if(extras != null)
        {
            idCategory = extras.getString("id");
            nameCategory = extras.getString("name");
        }

        ((TextView)rootView.findViewById(R.id.nameCategory)).setText(nameCategory);

        // Initialize recycler view
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_app);
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
                adapter = new MyRecyclerAdapterApps(getActivity(), appList);
                mRecyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(getActivity(), "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void parseResult(String result) {
        try {
            final String TAG_PARSE = "parseResult";

            final String OWN_ENTRY = "entry";
            final String OWN_CATEGORY = "category";
            final String OWN_ATTRIBUTE = "attributes";
            final String OWN_ID = "im:id";
            final String OWN_NEWS = "term";


            JSONObject response = new JSONObject(result);
            JSONObject feeds = response.getJSONObject("feed");
            JSONArray entry = feeds.getJSONArray(OWN_ENTRY);

            appList = new ArrayList<>();

            for (int i = 0; i < entry.length(); i++) {

                JSONObject forEntry = entry.optJSONObject(i);
                // Get id Category
                JSONObject categoryeObject = forEntry.getJSONObject(OWN_CATEGORY);
                JSONObject attributeObject = categoryeObject.getJSONObject(OWN_ATTRIBUTE);
                String id = attributeObject.getString(OWN_ID);
                Log.v(TAG_PARSE,"el id de categoria es: "+ idCategory + " el id de json es: " + id);
                //Get Name App
                JSONObject nameObject = forEntry.getJSONObject("im:name");
                String name = nameObject.getString("label");
                //Get Url image
                JSONObject image = forEntry.getJSONArray("im:image").getJSONObject(2);
                String urlImage = image.getString("label");
                //Get Summary
                JSONObject summaryObject = forEntry.getJSONObject("summary");
                String summary = summaryObject.getString("label");
                //Get Amount
                JSONObject priceObject = forEntry.getJSONObject("im:price");
                JSONObject attObject = priceObject.getJSONObject("attributes");
                String amount = attObject.getString("amount");
                String currency = attObject.getString("currency");
                //Get Artist
                JSONObject artistObject = forEntry.getJSONObject("im:artist");
                String artist = artistObject.getString("label");

                if(idCategory.equals(id))
                {
                    Apps item = new Apps();
                    item.setCategoryId(id);
                    item.setName(name);
                    item.setImageURL(urlImage);
                    item.setSummary(summary);
                    item.setAmount(amount);
                    item.setCurrency(currency);
                    item.setArtist(artist);
                    appList.add( item );
                }


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
