package com.tech.salesapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tech.salesapp.Entity.ProductList;
import com.tech.salesapp.Fragment.FragmentForCategories;
import com.tech.salesapp.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewGridProducts extends RecyclerView.Adapter<RecyclerviewGridProducts.MyViewHolder> {


    String personNames;
    String personImages;
    Context context;
    List<ProductList> productLists = new ArrayList<>();
    FragmentManager fragmentManager;

    public RecyclerviewGridProducts(Context context, List<ProductList> productLists,FragmentManager fragmentManager) {
        this.context = context;
        // this.personNames = personNames;
        this.productLists = productLists;
        this.fragmentManager=fragmentManager;
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
    public void onBindViewHolder(@NonNull final MyViewHolder viewHolder, int i) {
        //public void onBindViewHolder(@NonNull final MyViewHolder holder, final int i) {
        // set the data in items
        //holder.name.setText(personNames.get(position));
        //holder.image.setImageResource(personImages.get(position));
        // implement setOnClickListener event on item view.

        if (productLists.get(i).getName() != null && !productLists.get(i).getName().equalsIgnoreCase("")) {
            viewHolder.tv_productname.setText(productLists.get(i).getName());
        }

        if (productLists.get(i).getFile() != null && !productLists.get(i).getFile().equalsIgnoreCase("")) {

            Picasso.with(context).load(productLists.get(i).getFile()).error(R.drawable.picforgrid).placeholder(R.drawable.picforgrid).into(viewHolder.iv_data);

        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open another activity on item click
                FragmentForCategories fragmentProductListCart = new FragmentForCategories();
                FragmentManager manager = fragmentManager;
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.ll_for_fragments, fragmentProductListCart);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {

        if (productLists.size() <= 4) {
            return productLists.size();
        } else {
            return 4;
        }
        //later on we will change to its list size
    }

  /*  public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        public TextView tv_productname;
        public ImageView iv_data;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            tv_productname = (TextView) itemView.findViewById(R.id.tv_productname);
            iv_data = (ImageView) itemView.findViewById(R.id.iv_data);
        }
    }*/


    public class MyViewHolder extends RecyclerView.ViewHolder {

        // init the item view's
        public TextView tv_productname;
        public ImageView iv_data;

        public MyViewHolder(@NonNull View view) {
            super(view);
            tv_productname = (TextView) view.findViewById(R.id.tv_productname);
            iv_data = (ImageView) view.findViewById(R.id.iv_data);
        }
    }


}
