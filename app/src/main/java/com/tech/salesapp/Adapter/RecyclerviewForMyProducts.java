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

import com.squareup.picasso.Picasso;
import com.tech.salesapp.Activity.ProductsActivity;
import com.tech.salesapp.Entity.BrandList;
import com.tech.salesapp.R;

import java.util.List;

public class RecyclerviewForMyProducts extends RecyclerView.Adapter<RecyclerviewForMyProducts.MyViewHolder>  {

    String personNames;
    String personImages;
    Context context;
    List<BrandList>LSTBrand_List;

    public RecyclerviewForMyProducts(Context context, List<BrandList>LSTBrand_List) {
        this.context = context;
        this.LSTBrand_List=LSTBrand_List;

    }

    @Override
    public RecyclerviewForMyProducts.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_grid, parent, false);
        // set the view's size, margins, paddings and layout parameters
        RecyclerviewForMyProducts.MyViewHolder vh = new RecyclerviewForMyProducts.MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        if (LSTBrand_List.get(i).getName() != null && !LSTBrand_List.get(i).getName().equalsIgnoreCase("")) {
            myViewHolder.tv_productname.setText(LSTBrand_List.get(i).getName());
        }

        if (LSTBrand_List.get(i).getImage() != null && !LSTBrand_List.get(i).getImage().equalsIgnoreCase("")) {

            Picasso.with(context).load(LSTBrand_List.get(i).getImage()).error(R.drawable.picforgrid).placeholder(R.drawable.picforgrid).into(myViewHolder.iv_data);

        }

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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
        return LSTBrand_List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        public TextView tv_productname;
        public ImageView iv_data;

        public MyViewHolder(View view) {
            super(view);
            // get the reference of item view's
            tv_productname = (TextView) view.findViewById(R.id.tv_productname);
            iv_data = (ImageView) view.findViewById(R.id.iv_data);
        }
    }


}
