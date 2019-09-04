package com.tech.salesapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tech.salesapp.Activity.CartActivity;
import com.tech.salesapp.Activity.CategoriesActivity;
import com.tech.salesapp.Activity.ProductDetailActivity;
import com.tech.salesapp.Activity.ProductsActivity;
import com.tech.salesapp.Adapter.RecyclerviewForBestSellers;
import com.tech.salesapp.Adapter.RecyclerviewForProducts;
import com.tech.salesapp.Adapter.RecyclerviewGridProducts;
import com.tech.salesapp.Fragment.FragmentCart;
import com.tech.salesapp.Fragment.FragmentForCategories;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText et_search;
    RecyclerView recyclerView, rv_forBestSellers;
    TextView seeAllDeals;
    LinearLayout ll_dashboard, ll_for_fragments;
    ImageView iv_dealoftheday;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        //recyclerView for features--categories
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        RecyclerviewGridProducts customAdapter = new RecyclerviewGridProducts(MainActivity.this, "", "");
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

        //recyclerView for bestSellers
        rv_forBestSellers = findViewById(R.id.rv_forBestSellers);
        RecyclerviewForBestSellers adapter = new RecyclerviewForBestSellers(this, "");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rv_forBestSellers.setLayoutManager(layoutManager);
        rv_forBestSellers.setAdapter(adapter);

        seeAllDeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                startActivity(intent);

              /*  ll_dashboard.setVisibility(View.GONE);
                ll_for_fragments.setVisibility(View.VISIBLE);

                FragmentForCategories fragmentCart = new FragmentForCategories();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.ll_for_fragments, fragmentCart);
                transaction.commit();*/

            }
        });
        iv_dealoftheday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
                startActivity(intent);
            }
        });


    }

    private void findViewById() {
        et_search = (EditText) findViewById(R.id.et_search);
        recyclerView = (RecyclerView) findViewById(R.id.rv_products);
        seeAllDeals = (TextView) findViewById(R.id.seeAllDeals);
        ll_dashboard = (LinearLayout) findViewById(R.id.ll_dashboard);
        ll_for_fragments = (LinearLayout) findViewById(R.id.ll_for_fragments);
        iv_dealoftheday=(ImageView)findViewById(R.id.iv_dealoftheday);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_cart) {

            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
            /*ll_dashboard.setVisibility(View.GONE);
            ll_for_fragments.setVisibility(View.VISIBLE);

            FragmentCart fragmentCart = new FragmentCart();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.ll_for_fragments, fragmentCart);
            transaction.commit();*/

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            ll_dashboard.setVisibility(View.VISIBLE);
            ll_for_fragments.setVisibility(View.GONE);

            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav_yourorders) {

            //Intent intent = new Intent(this, ProductDetailActivity.class);
            //startActivity(intent);
            //ll_dashboard.setVisibility(View.GONE);
           // ll_for_fragments.setVisibility(View.VISIBLE);
            // Create the transaction
            // FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
            // fts.replace(R.id.ll_for_fragments, new FragmentCart());
            //fts.addToBackStack("optional tag");
            //fts.commit();

        } else if (id == R.id.nav_youraccount) {

        } else if (id == R.id.nav_categories) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
