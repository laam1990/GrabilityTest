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

        private Category item;

        public CustomViewHolder(View view) {
            super(view);

            view.setOnClickListener(this);

            this.textView = (TextView) view.findViewById(R.id.categoryText);

            this.card = (CardView)view.findViewById(R.id.cardViewCategory);
        }

        public void setFeed(Category item, MainActivity activity){

            //Store Category Item
            this.item = item;
            //Store Activity
            this.activity = activity;

            textView.setText(item.getTerm());

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
