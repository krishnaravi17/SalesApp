package com.tech.salesapp.Activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.tech.salesapp.Adapter.RecyclerviewForProducts;
import com.tech.salesapp.Entity.RetroPhoto;
import com.tech.salesapp.R;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {

    RecyclerView rv_products;
    private RecyclerviewForProducts adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        findViewById();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Cheese");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorGray, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorGray));
        }

       // getActionBar().setDisplayShowHomeEnabled(true);
       // getActionBar().setHomeButtonEnabled(true);
       // getActionBar().setDisplayHomeAsUpEnabled(true);

        List<RetroPhoto> lstretroPhotos = new ArrayList<>();
        RetroPhoto photoList = new RetroPhoto();

        for (int i = 0; i <= 5; i++) {
            photoList.setId("1");
            photoList.setName("Ravi");
            lstretroPhotos.add(photoList);
        }


        rv_products = findViewById(R.id.rv_products);
        adapter = new RecyclerviewForProducts(this, lstretroPhotos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ProductsActivity.this);
        rv_products.setLayoutManager(layoutManager);
        rv_products.setAdapter(adapter);


    }

    private void findViewById() {
        rv_products = (RecyclerView) findViewById(R.id.rv_products);
    }
}
