package com.tech.salesapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tech.salesapp.Activity.ProductsActivity;
import com.tech.salesapp.R;

import java.util.ArrayList;

public class RecyclerviewGridProducts extends RecyclerView.Adapter {

    String personNames;
    String personImages;
    Context context;

    public RecyclerviewGridProducts(Context context, String personNames, String personImages) {
        this.context = context;
        this.personNames = personNames;
        this.personImages = personImages;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_grid, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        // set the data in items
        //holder.name.setText(personNames.get(position));
        //holder.image.setImageResource(personImages.get(position));
        // implement setOnClickListener event on item view.
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open another activity on item click

                Intent intent = new Intent(context, ProductsActivity.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView name;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            //name = (TextView) itemView.findViewById(R.id.);
            //image = (ImageView) itemView.findViewById(R.id.image);
        }
    }


}
