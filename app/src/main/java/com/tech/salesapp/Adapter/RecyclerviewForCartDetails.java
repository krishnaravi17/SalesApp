package com.tech.salesapp.Adapter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tech.salesapp.Entity.ProductList;
import com.tech.salesapp.R;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;
import static com.tech.salesapp.MainActivity.mCartItemCount;

public class RecyclerviewForCartDetails extends RecyclerView.Adapter<RecyclerviewForCartDetails.CustomViewHolder> {

    List<ProductList> productLists = new ArrayList<>();
    //public MainActivity activity=new MainActivity();
    FragmentManager fragmentManager;
    Activity context;
    //Activity activity=new MainActivity();
    int totalPrice=0;
    TextView tv_price;

    public RecyclerviewForCartDetails(Activity context, FragmentManager fragmentManager, List<ProductList> productLists) {
        this.context = context;
        this.productLists = productLists;
        this.fragmentManager = fragmentManager;
        //this.tv_price=tv_price;
    }

    @Override
    public RecyclerviewForCartDetails.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_for_cartdetails, viewGroup, false);
        return new RecyclerviewForCartDetails.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, final int position) {
        String euro = Currency.getInstance(Locale.GERMANY).getCurrencyCode();
        //totalPrice= totalPrice+Integer.parseInt(productLists.get(position).getPrice());
        //tv_price.setText(String.valueOf(totalPrice));

        if (productLists.get(position) != null && productLists.get(position).getName() != null && !productLists.get(position).getName().equalsIgnoreCase("")) {
            holder.tv_productname.setText(productLists.get(position).getName());
        } else {
            holder.tv_productname.setText("Product");
        }
        if (productLists.get(position).getPrice()!= 0 ) {
            holder.tv_productprice.setText(String.valueOf(productLists.get(position).getPrice()));
        } else {
            holder.tv_productprice.setText("$100.0");
        }

        //holder.sno.setText(String.valueOf(position + 1));

        if (productLists.get(position).getFile() != null && !productLists.get(position).getFile().equalsIgnoreCase("")) {

            Picasso.with(context).load(productLists.get(position).getFile()).error(R.drawable.picforbestseller).placeholder(R.drawable.picforbestseller).into(holder.productimage);

        }

        if(productLists.get(position).getQuantity()!=0){
            String count= String.valueOf(productLists.get(position).getQuantity());
            holder.num.setText(count);
        }


        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = String.valueOf(holder.num.getText());
                int value1 = Integer.parseInt(value);
                String value2 = String.valueOf(value1 - 1);
                holder.num.setText(value2);


            }
        });

        final ProductList objproductList = productLists.get(position);

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String value = String.valueOf(holder.num.getText());
                int value1 = Integer.parseInt(value);
                String value2 = String.valueOf(value1 + 1);
                holder.num.setText(value2);

                addProduct(objproductList);


            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.belowlayout.setVisibility(View.INVISIBLE);

            }
        });


    }



    @Override
    public int getItemCount() {
        //return productLists.size()/*dataList.size()*/;
        return productLists.size();

    }

    public void addProduct(ProductList product) {

        SharedPreferences prefs = context.getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        String productsList = prefs.getString("Products", null);
        if (productsList == null) {
            ArrayList<ProductList> mproductsList = new ArrayList<ProductList>();
            editor.putString("Products", ProductList.listToJson(mproductsList));
            editor.apply();
            productsList = prefs.getString("Products", null);
        }
        List<ProductList> productList = ProductList.getListDataFromJson(productsList);

        if (productList.contains(product)) {
            ProductList item = productList.get(productList.indexOf(product));
            int count = item.getQuantity() + 1;
            item.setQuantity(count);

        } else {

            product.setQuantity(1);
            productList.add(product);
        }


        String json = ProductList.listToJson(productList);

        mCartItemCount = productList.size();

        editor.putInt("mCartItemCount", mCartItemCount);//storing count value
        editor.putString("Products", json);
        editor.apply();

        context.invalidateOptionsMenu();

    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView sno, tv_productname, tv_productprice, num;
        private ImageView productimage;
        ImageView minus, add, remove;
        LinearLayout belowlayout;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            //sno = mView.findViewById(R.id.sno);
            tv_productname = mView.findViewById(R.id.tv_productname);
            tv_productprice = mView.findViewById(R.id.tv_productprice);
            productimage = mView.findViewById(R.id.productimage);

            minus = mView.findViewById(R.id.minus);
            add = mView.findViewById(R.id.plus);
            num = mView.findViewById(R.id.num);
            remove = mView.findViewById(R.id.remove);
            belowlayout = mView.findViewById(R.id.belowlayout);
        }
    }


}
