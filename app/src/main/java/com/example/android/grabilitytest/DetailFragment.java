package com.example.android.grabilitytest;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by Luis Adrian on 13/04/2016.
 */
public class DetailFragment extends Fragment {

    private String idCategory,nameCategory,imageUrl,summary,amount,currency,artist,nameApp;
    private ImageView imageApp;

    public DetailFragment() {
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

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);


        Bundle extras = getActivity().getIntent().getExtras();
        if(extras != null)
        {
            idCategory = extras.getString("id");
            nameCategory = extras.getString("nameCategory");
            nameApp = extras.getString("nameApp");
            imageUrl = extras.getString("imageUrl");
            summary = extras.getString("summary");
            amount = extras.getString("amount");
            currency = extras.getString("currency");
            artist = extras.getString("artist");
        }

        imageApp = (ImageView)rootView.findViewById(R.id.imageUrl);

        ((TextView)rootView.findViewById(R.id.nameApp)).setText(nameApp);
        ((TextView)rootView.findViewById(R.id.nameArtist)).setText(artist);
        ((TextView)rootView.findViewById(R.id.nameCategory)).setText(nameCategory);
        ((TextView)rootView.findViewById(R.id.amount)).setText(amount);
        ((TextView)rootView.findViewById(R.id.currency)).setText(currency);
        ((TextView)rootView.findViewById(R.id.summary)).setText(summary);

        Glide.with(getContext()).load(imageUrl).asBitmap().into(new BitmapImageViewTarget(imageApp)
        {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageApp.setImageDrawable(circularBitmapDrawable);
            }
        });

        return rootView;
    }

}
