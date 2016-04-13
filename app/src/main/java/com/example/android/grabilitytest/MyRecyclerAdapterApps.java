package com.example.android.grabilitytest;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Luis Adrian on 12/04/2016.
 */
public class MyRecyclerAdapterApps extends RecyclerView.Adapter<MyRecyclerAdapterApps.CustomViewHolder> {

    private List<Apps> appsItemList;
    private Context context;
    private Long amountNumber;

    public MyRecyclerAdapterApps(Context context, List<Apps> appsItemList) {
        this.appsItemList = appsItemList;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_apps,viewGroup,false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {

        customViewHolder.setFeed(appsItemList.get(i),(AppsActivity)context);

    }

    @Override
    public int getItemCount() {
        return (null != appsItemList ? appsItemList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView nameApp,amountApp,currencyApp;
        protected ImageView imageUrl;
        protected AppsActivity activity;
        protected CardView card;

        private Apps item;

        public CustomViewHolder(View view) {
            super(view);

            view.setOnClickListener(this);

            this.nameApp = (TextView) view.findViewById(R.id.nameApp);
            this.amountApp = (TextView) view.findViewById(R.id.amount);
            this.currencyApp = (TextView) view.findViewById(R.id.currency);
            this.card = (CardView)view.findViewById(R.id.cardViewApps);
            this.imageUrl = (ImageView)view.findViewById(R.id.imageUrl);
        }

        public void setFeed(Apps item, AppsActivity activity){


            //Store Apps Item
            this.item = item;
            //Store Activity
            this.activity = activity;

            nameApp.setText(item.getName());

            Glide.with(context)
                    .load(item.getImageURL()).asBitmap()
                    .centerCrop()
                    .into(imageUrl);
            /*if(amountNumber!=0)
            amountNumber = amountNumber.parseLong(item.getAmount());

            if(amountNumber>0)
            {
                amountApp.setVisibility(View.GONE);
                currencyApp.setText("FREE");
            }
            else
            {*/
                amountApp.setText(item.getAmount());
                currencyApp.setText(item.getCurrency());
            //}

        }

        @Override
        public void onClick(View v) {


            /*Intent intent = new Intent(context, Apps.class);
            intent.putExtra("id", item.getId());
            intent.putExtra("name",item.getTerm());
            context.startActivity(intent);

            Toast.makeText(context, "El id es: " + item.getId(), Toast.LENGTH_LONG).show();*/

        }
    }

}
