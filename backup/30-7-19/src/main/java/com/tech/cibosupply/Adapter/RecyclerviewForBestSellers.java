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

import com.tech.salesapp.Activity.ProductDetailActivity;
import com.tech.salesapp.Activity.ProductsActivity;
import com.tech.salesapp.Entity.RetroPhoto;
import com.tech.salesapp.R;

import java.util.List;

public class RecyclerviewForBestSellers  extends RecyclerView.Adapter<RecyclerviewForBestSellers.CustomViewHolder> {

    String dataList;
    private Context context;

    public RecyclerviewForBestSellers(Context context, String dataList){
        this.context = context;
        this.dataList = dataList;
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
        // holder.txtTitle.setText(dataList.get(position).getName());

        /*Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getImageurl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.coverImage);
*/
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ProductDetailActivity.class);
                context.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return 5/*dataList.size()*/;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtTitle;
        private ImageView coverImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            //txtTitle = mView.findViewById(R.id.title);
            //coverImage = mView.findViewById(R.id.coverImage);
        }
    }

}
