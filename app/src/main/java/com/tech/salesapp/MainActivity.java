package com.tech.salesapp;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
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
import android.widget.Toast;

import com.tech.salesapp.Entity.ProductList;
import com.tech.salesapp.Entity.RetroPhoto;
import com.tech.salesapp.Fragment.FragmentForCategories;
import com.tech.salesapp.Fragment.FragmentForTotalCarts;
import com.tech.salesapp.Fragment.FragmentDashboard;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText et_search;
    RecyclerView recyclerView, rv_forBestSellers, rv_search;
    TextView seeAllDeals;
    LinearLayout ll_dashboard, ll_for_fragments;
    ImageView iv_dealoftheday;
    RetroPhoto retroPhoto = new RetroPhoto();
    List<RetroPhoto> lstretro = new ArrayList<>();
    List<RetroPhoto> newlstforrv = new ArrayList<>();
    int count = 0;

    public static TextView textCartItemCount;
    public static int mCartItemCount = 0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // findViewById();

        SharedPreferences prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        mCartItemCount = prefs.getInt("mCartItemCount", 0);

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

        FragmentDashboard fragmentCart = new FragmentDashboard();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.ll_for_fragments, fragmentCart);
        transaction.commit();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            // super.onBackPressed();
        }


        if (count == 0) {

            count++;

            FragmentDashboard fragmentCart = new FragmentDashboard();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.ll_for_fragments, fragmentCart);
            transaction.commit();

            Toast.makeText(MainActivity.this, "Press Back again to Exit!!!", Toast.LENGTH_SHORT).show();

            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(2000);

                        count = 0;

                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        //CustomToast.toast(activity, "" + e.getMessage(), 0);
                    }
                }
            };
            thread.start();
        } else {

            /*Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            a.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(a);*/
            super.onBackPressed();
            System.exit(0);
           /* android.os.Process.killProcess(android.os.Process.myPid());
            super.onDestroy();*/
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_cart);

        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);

        setupBadge();

        // mCartItemCount=//here need to get value from sharedpref of cart count

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });


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

            SharedPreferences prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
            String productsList = prefs.getString("Products", null);//taking data from sharedPrefs

            List<ProductList> data = ProductList.getListDataFromJson(productsList);

            if (data != null && data.size() > 0) {

                FragmentForTotalCarts fragmentProductList = new FragmentForTotalCarts();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.ll_for_fragments, fragmentProductList);
                transaction.commit();

            } else {

                Toast.makeText(MainActivity.this, "Data Not Found!!", Toast.LENGTH_SHORT).show();

            }



            return true;
        }

        return super.onOptionsItemSelected(item);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            //ll_dashboard.setVisibility(View.VISIBLE);
            //ll_for_fragments.setVisibility(View.GONE);

            FragmentDashboard fragmentCart = new FragmentDashboard();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.ll_for_fragments, fragmentCart);
            transaction.commit();

            // Handle the camera action
        } else if (id == R.id.nav_yourorders) {

            Toast.makeText(MainActivity.this, "Work is in Progress!!", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_youraccount) {

            Toast.makeText(MainActivity.this, "Work is in Progress!!", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_categories) {

            FragmentForCategories fragmentProductListCart = new FragmentForCategories();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.ll_for_fragments, fragmentProductListCart);
            transaction.commit();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

   /* public void addProduct(ProductList product) {

        SharedPreferences prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        String productsList = prefs.getString("Products", null);
        if (productsList == null) {
            ArrayList<ProductList> mproductsList = new ArrayList<ProductList>();
            editor.putString("Products", ProductList.listToJson(mproductsList));
            editor.apply();
            productsList= prefs.getString("Products", null);
        }
        List<ProductList> productList = ProductList.getListDataFromJson(productsList);

        if (productList.contains(product)) {
            ProductList item = productList.get(productList.indexOf(product));
            int count = item.getCount() + 1;
            item.setCount(count);

        } else {
            product.setCount(1);
            productList.add(product);
        }

        String json = ProductList.listToJson(productList);

        editor.putString("Products", json);
        editor.apply();

        mCartItemCount=productList.size();
        invalidateOptionsMenu();

    }*/


}
