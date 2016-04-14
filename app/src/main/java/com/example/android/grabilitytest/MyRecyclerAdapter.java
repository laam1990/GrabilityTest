package com.example.android.grabilitytest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
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

            switch (item.getTerm()){
                case "Games":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.games));
                    break;
                case "Books":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.books));
                    break;
                case "Business":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.business));
                    break;
                case "Catalogs":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.catalog));
                    break;
                case "Education":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.education));
                    break;
                case "Entertainment":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.enterteiment));
                    break;
                case "Finance":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.finance));
                    break;
                case "Food & Drink":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.fooddrink));
                    break;
                case "Health & Fitness":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.healthfitness));
                    break;
                case "Lifestyle":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.lifestyle));
                    break;
                case "Medical":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.medical));
                    break;
                case "Music":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.music));
                    break;
                case "Navigation":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.navigation));
                    break;
                case "News":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.news));
                    break;
                case "Newsstand":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.newsstand));
                    break;
                case "Photo & Video":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.photovideo));
                    break;
                case "Productivity":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.productivity));
                    break;
                case "Reference":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.reference));
                    break;
                case "Social Networking":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.socialnetwork));
                    break;
                case "Sports":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.sports));
                    break;
                case "Travel":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.travel));
                    break;
                case "Utilities":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.utilities));
                    break;
                case "Weather":
                    imageView.setBackground(context.getResources().getDrawable(R.drawable.weather));
                    break;

            }

        }

        @Override
        public void onClick(View v) {


            Intent intent = new Intent(context, AppsActivity.class);
            intent.putExtra("id", item.getId());
            intent.putExtra("name",item.getTerm());
            context.startActivity(intent);

            Toast.makeText(context,"El id es: "+item.getId(),Toast.LENGTH_LONG).show();


        }
    }


}
