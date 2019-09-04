package com.tech.salesapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tech.salesapp.Entity.ProductList;
import com.tech.salesapp.R;

import java.util.List;

public class RecyclerviewForSearch extends RecyclerView.Adapter<RecyclerviewForSearch.CustomViewHolder> {

    List<ProductList> dataList;
    private Context context;

    public RecyclerviewForSearch(Context context, List<ProductList> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public RecyclerviewForSearch.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_for_search, viewGroup, false);
        return new RecyclerviewForSearch.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerviewForSearch.CustomViewHolder holder, int position) {
        // holder.txtTitle.setText(dataList.get(position).getName());

        /*Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getImageurl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.coverImage);
*/
        if (dataList.size() > 0) {
            holder.name.setText(dataList.get(position).getName());
        }else{
            holder.name.setText("");
        }


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(context, ProductDetailActivity.class);
                //context.startActivity(intent);
                Toast.makeText(context, "Clicked!!", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView name;
        private ImageView coverImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            name = mView.findViewById(R.id.name);
            //coverImage = mView.findViewById(R.id.coverImage);
        }
    }


}
