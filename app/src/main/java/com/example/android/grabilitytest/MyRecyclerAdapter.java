package com.example.android.grabilitytest;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

/**
 * Created by Luis Adrian on 08/04/2016.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder> {

    private List<Category> categoryItemList;
    private Context context;

    public MyRecyclerAdapter(Context context, List<Category> categoryItemList) {
        this.categoryItemList = categoryItemList;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category, null);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {

        customViewHolder.setFeed(categoryItemList.get(i),(MainActivity)context);

    }

    @Override
    public int getItemCount() {
        return (null != categoryItemList ? categoryItemList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected String completeUrl;
        protected TextView textView;
        protected RelativeLayout layout;
        protected MainActivity activity;
        protected CardView card;
        protected ImageView imageView;

        private Category item;

        public CustomViewHolder(View view) {
            super(view);

            view.setOnClickListener(this);

            this.textView = (TextView) view.findViewById(R.id.categoryText);
            this.imageView = (ImageView) view.findViewById(R.id.imageCategory);
            this.card = (CardView)view.findViewById(R.id.cardViewCategory);
        }

        public void setFeed(Category item, MainActivity activity){

            //Store Category Item
            this.item = item;
            //Store Activity
            this.activity = activity;

            textView.setText(item.getTerm());

            String baseUrl = "http://i.imgur.com/";
            String books = "f6h8wRC.jpg";
            String games = "eX4etK2.jpg";
            String business = "tYsxuug.jpg";
            String catalogs = "rqnqprW.jpg";
            String education = "dPPYKsC.png";
            String entertainment = "FtIWAS2.jpg";
            String finance = "YQwSQQZ.png";
            String foodrink = "MczMKCH.jpg";
            String healthfitness = "P7gQ3Ck.jpg";
            String lifestyle = "0hMcsjC.jpg";
            String medical = "SXw2nrg.png";
            String music = "1dgqDcd.jpg";
            String navigation = "BuNFS3l.jpg";
            String news = "6uutFPw.jpg";
            String newsstand = "znRq2du.jpg";
            String photovideo = "xS4lrq1.jpg";
            String productivity = "on2KJQO.jpg";
            String reference = "UtOPvRH.jpg";
            String socialnetworking = "AjmjuWH.jpg";
            String sports = "LblJ0fA.jpg";
            String travel = "6KjlGve.jpg";
            String utilities = "SNDXMbL.png";
            String weather = "RT8jZKg.png";

            switch (item.getTerm()){
                case "Games":
                    LoadImage(baseUrl,games,imageView);
                    completeUrl = baseUrl+games;
                    break;
                case "Books":
                    LoadImage(baseUrl, books, imageView);
                    completeUrl = baseUrl+books;
                    break;
                case "Business":
                    LoadImage(baseUrl, business, imageView);
                    completeUrl = baseUrl+business;
                    break;
                case "Catalogs":
                    LoadImage(baseUrl, catalogs, imageView);
                    completeUrl = baseUrl+catalogs;
                    break;
                case "Education":
                    LoadImage(baseUrl, education, imageView);
                    completeUrl = baseUrl+education;
                    break;
                case "Entertainment":
                    LoadImage(baseUrl, entertainment, imageView);
                    completeUrl = baseUrl+entertainment;
                    break;
                case "Finance":
                    LoadImage(baseUrl, finance, imageView);
                    completeUrl = baseUrl+finance;
                    break;
                case "Food & Drink":
                    LoadImage(baseUrl, foodrink, imageView);
                    completeUrl = baseUrl+foodrink;
                    break;
                case "Health & Fitness":
                    LoadImage(baseUrl, healthfitness, imageView);
                    completeUrl = baseUrl+healthfitness;
                    break;
                case "Lifestyle":
                    LoadImage(baseUrl, lifestyle, imageView);
                    completeUrl = baseUrl+lifestyle;
                    break;
                case "Medical":
                    LoadImage(baseUrl, medical, imageView);
                    completeUrl = baseUrl+medical;
                    break;
                case "Music":
                    LoadImage(baseUrl, music, imageView);
                    completeUrl = baseUrl+music;
                    break;
                case "Navigation":
                    LoadImage(baseUrl, navigation, imageView);
                    completeUrl = baseUrl+navigation;
                    break;
                case "News":
                    LoadImage(baseUrl, news, imageView);
                    completeUrl = baseUrl+news;
                    break;
                case "Newsstand":
                    LoadImage(baseUrl, newsstand, imageView);
                    completeUrl = baseUrl+newsstand;
                    break;
                case "Photo & Video":
                    LoadImage(baseUrl, photovideo, imageView);
                    completeUrl = baseUrl+photovideo;
                    break;
                case "Productivity":
                    LoadImage(baseUrl, productivity, imageView);
                    completeUrl = baseUrl+productivity;
                    break;
                case "Reference":
                    LoadImage(baseUrl, reference, imageView);
                    completeUrl = baseUrl+reference;
                    break;
                case "Social Networking":
                    LoadImage(baseUrl, socialnetworking, imageView);
                    completeUrl = baseUrl+socialnetworking;
                    break;
                case "Sports":
                    LoadImage(baseUrl, sports, imageView);
                    completeUrl = baseUrl+sports;
                    break;
                case "Travel":
                    LoadImage(baseUrl, travel, imageView);
                    completeUrl = baseUrl+travel;
                    break;
                case "Utilities":
                    LoadImage(baseUrl, utilities, imageView);
                    completeUrl = baseUrl+utilities;
                    break;
                case "Weather":
                    LoadImage(baseUrl, weather, imageView);
                    completeUrl = baseUrl+weather;
                    break;

            }

        }

        @Override
        public void onClick(View v) {


            Intent intent = new Intent(context, AppsActivity.class);
            intent.putExtra("id", item.getId());
            intent.putExtra("name",item.getTerm());
            intent.putExtra("urlHeader", completeUrl);

            View sharedView = v;
            String transitionName = context.getResources().getString(R.string.category_transition_name);

            ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(activity, sharedView, transitionName);

            context.startActivity(intent, transitionActivityOptions.toBundle());

        }

        public void LoadImage(String baseUrl, String categoryUrl, final ImageView image) {
            String completeUrl = baseUrl + categoryUrl;

            Glide.with(context).load(completeUrl).asBitmap().centerCrop().into(new BitmapImageViewTarget(image) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    image.setImageDrawable(circularBitmapDrawable);
                }
            });
        }
    }


}
