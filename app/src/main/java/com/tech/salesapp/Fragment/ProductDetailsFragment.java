package com.tech.salesapp.Fragment;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tech.salesapp.Entity.ProductList;
import com.tech.salesapp.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.tech.salesapp.MainActivity.mCartItemCount;
import static com.tech.salesapp.MainActivity.textCartItemCount;

public class ProductDetailsFragment extends Fragment {

    TextView tv_AddToCart;
    ProductList productList;

    public ProductDetailsFragment() {

    }

    @SuppressLint("ValidFragment")
    public ProductDetailsFragment(ProductList productList) {
        this.productList = productList;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_product_details, container, false);
        tv_AddToCart = v.findViewById(R.id.tv_AddToCart);

        tv_AddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addProduct(productList);

                //mCartItemCount++;

            }
        });

        return v;
    }


    public void addProduct(ProductList product) {

        SharedPreferences prefs = getActivity().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
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
            //product.setQuantity(count);

        } else {
            product.setQuantity(1);
            productList.add(product);
        }


        String json = ProductList.listToJson(productList);

        mCartItemCount = productList.size();

        editor.putInt("mCartItemCount", mCartItemCount);//storing count value
        editor.putString("Products", json);
        editor.apply();

        getActivity().invalidateOptionsMenu();

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);

        getActivity().getMenuInflater().inflate(R.menu.main, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);

        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });


    }

    private void setupBadge() {

        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

}
