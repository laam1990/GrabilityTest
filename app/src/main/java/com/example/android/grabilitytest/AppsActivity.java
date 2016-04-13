package com.example.android.grabilitytest;


import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class AppsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new AppsFragment())
                    .commit();
        }
    }

}
