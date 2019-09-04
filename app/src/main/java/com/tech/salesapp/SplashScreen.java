package com.tech.salesapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.tech.salesapp.Entity.ProductList;
import com.tech.salesapp.Interface.ApiService;
import com.tech.salesapp.Network.ApiClient;
import com.tech.salesapp.Utils.AndroidUtils;
import com.tech.salesapp.Utils.SharedDataPrefs;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tech.salesapp.Interface.InterfaceForListdata.LSTProduct_List;

public class SplashScreen extends AppCompatActivity {
    ProgressDialog progressDialog;
    String userStatus = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorGray, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorGray));
        }
        progressDialog = new ProgressDialog(SplashScreen.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        if (AndroidUtils.checkYourMobileDataConnection(getApplicationContext())) {
            new Timer().schedule(new MyTimer(getApplicationContext()), 3000);
            return;
        } else {


        }


        Toast.makeText(getApplicationContext(), "No Data Connectivity Available", Toast.LENGTH_LONG).show();
    }

    private void getProductList(String UserID) {

        if (AndroidUtils.checkYourMobileDataConnection(SplashScreen.this)) {

            ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);

            //final ProgressDialog dialog = AppProgress.showProgress(SplashScreen.this);


            Call<JsonObject> call = apiService.ProductData("2", "1");//merchant id

            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                    if (response != null && response.body().get("message").toString().equalsIgnoreCase("\"Request is successfull!\"")) {

                        List<ProductList> productLists = ProductList.getListDataFromJson(response.body().get("data").toString());

                        for (ProductList productList : productLists) {

                            LSTProduct_List.add(productList);

                        }

                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                        Toast.makeText(SplashScreen.this, "Fetched Details!!", Toast.LENGTH_LONG).show();

                    } else {
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        Toast.makeText(SplashScreen.this, "Some thing went wrong!!", Toast.LENGTH_SHORT).show();
                    }

                    progressDialog.dismiss();
                    //AppProgress.hideProgress(dialog);
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    //AppProgress.hideProgress(dialog);
                    progressDialog.dismiss();
                }
            });


        } else {
            Toast.makeText(this, getResources().getString(R.string.Internet_Connection_Not_Available), Toast.LENGTH_SHORT).show();
        }


    }

    class MyTimer extends TimerTask {
        Context context;

        public MyTimer(Context context) {
            this.context = context;
        }

        public void run() {
            //progressDialog.dismiss();
            SharedPreferences shp = getSharedPreferences(SharedDataPrefs.Shared_Prefrence_Name, MODE_PRIVATE);

            String UserID = shp.getString(SharedDataPrefs.UserID, "0");

            if (UserID != null && UserID.equalsIgnoreCase("0")) {
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
                getProductList("2");
            }
        }
    }
}
