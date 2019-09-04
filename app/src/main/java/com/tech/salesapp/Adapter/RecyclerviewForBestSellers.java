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
import com.tech.salesapp.Fragment.ProductDetailsFragment;
import com.tech.salesapp.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewForBestSellers extends RecyclerView.Adapter<RecyclerviewForBestSellers.CustomViewHolder> {

    List<ProductList> productLists = new ArrayList<>();
    private Context context;
    FragmentManager fragmentManager;;

    public RecyclerviewForBestSellers(Context context, FragmentManager fragmentManager, List<ProductList> productLists) {
        this.context = context;
        this.productLists = productLists;
        this.fragmentManager=fragmentManager;
    }

    @NonNull
    @Override
    public RecyclerviewForBestSellers.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_forbestsellers, viewGroup, false);
        return new RecyclerviewForBestSellers.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerviewForBestSellers.CustomViewHolder holder, int position) {


        if (productLists.get(position).getName() != null && !productLists.get(position).getName().equalsIgnoreCase("")) {
            holder.tv_productname.setText(productLists.get(position).getName());
        } else {
            holder.tv_productname.setText("Product");
        }
        if (productLists.get(position).getPrice() != 0 ) {
            String var= String.valueOf(productLists.get(position).getPrice());
            holder.tv_productprice.setText(var);
        } else {
            holder.tv_productprice.setText("$100.0");
        }

        holder.sno.setText(String.valueOf(position + 1));

        if (productLists.get(position).getFile() != null && !productLists.get(position).getFile().equalsIgnoreCase("")) {

            Picasso.with(context).load(productLists.get(position).getFile()).error(R.drawable.picforbestseller).placeholder(R.drawable.picforbestseller).into(holder.productimage);

        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(context, ProductDetailActivity.class);
               // context.startActivity(intent);
                ProductDetailsFragment fragmentCart = new ProductDetailsFragment();
                FragmentManager manager = fragmentManager;
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.ll_for_fragments, fragmentCart);
                transaction.commit();

            }
        });


    }

    @Override
    public int getItemCount() {
        //return productLists.size()/*dataList.size()*/;
        if (productLists.size() <= 4) {
            return productLists.size();
        } else {
            return 4;
        }
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView sno, tv_productname, tv_productprice;
        private ImageView productimage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            sno = mView.findViewById(R.id.sno);
            tv_productname = mView.findViewById(R.id.tv_productname);
            tv_productprice = mView.findViewById(R.id.tv_productprice);
            productimage = mView.findViewById(R.id.productimage);
        }
    }

}
