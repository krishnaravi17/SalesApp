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
import com.tech.salesapp.Entity.CategoriesList;
import com.tech.salesapp.Fragment.FragmentProductList;
import com.tech.salesapp.R;

import java.util.List;

public class RecyclerviewGridCategories extends RecyclerView.Adapter<RecyclerviewGridCategories.MyViewHolder> {

    String personNames;
    String personImages;
    Context context;
    FragmentManager fragmentManager;
    List<CategoriesList> categoriesListList;

    public RecyclerviewGridCategories(Context context, List<CategoriesList> categoriesListList, FragmentManager fragmentManager) {
        this.context = context;
        this.personNames = personNames;
        this.fragmentManager = fragmentManager;
        this.categoriesListList = categoriesListList;
    }

    @Override
    public RecyclerviewGridCategories.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_grid, parent, false);
        // set the view's size, margins, paddings and layout parameters
        RecyclerviewGridCategories.MyViewHolder vh = new RecyclerviewGridCategories.MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerviewGridCategories.MyViewHolder myViewHolder, int i) {

        //holder.image.setImageResource(personImages.get(position));
        // implement setOnClickListener event on item view.
        if (categoriesListList.get(i).getName() != null && !categoriesListList.get(i).getName().equalsIgnoreCase("")) {
            myViewHolder.tv_productname.setText(categoriesListList.get(i).getName());
        }


        if (categoriesListList.get(i).getImage() != null && !categoriesListList.get(i).getImage().equalsIgnoreCase("")) {

            Picasso.with(context).load(categoriesListList.get(i).getImage()).error(R.drawable.picforgrid).placeholder(R.drawable.picforgrid).into(myViewHolder.iv_data);

        }

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open another activity on item click

                //HERE WE NEED TO CALL GERDATAFROMPRODUCTLIST ACC TO CATEGORY ID

                FragmentProductList fragmentProductList = new FragmentProductList();
                FragmentManager manager = fragmentManager;
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.ll_for_fragments, fragmentProductList);
                transaction.commit();

            }
        });
    }


    @Override
    public int getItemCount() {
        return categoriesListList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView tv_productname;
        ImageView iv_data;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            iv_data = (ImageView) itemView.findViewById(R.id.iv_data);
            tv_productname = (TextView) itemView.findViewById(R.id.tv_productname);
        }
    }


}
