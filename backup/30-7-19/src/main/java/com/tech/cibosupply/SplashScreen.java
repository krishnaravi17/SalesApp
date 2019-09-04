package com.tech.salesapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.tech.salesapp.Utils.AndroidUtils;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {
    ProgressDialog progressDialog;
    String userStatus = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        progressDialog = new ProgressDialog(SplashScreen.this);
        progressDialog.setMessage("Please Wait...");
        //progressDialog.show();

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        if (AndroidUtils.checkYourMobileDataConnection(getApplicationContext())) {
            new Timer().schedule(new MyTimer(getApplicationContext()), 3000);
            return;
        } else {

            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        Toast.makeText(getApplicationContext(), "No Data Connectivity Available", Toast.LENGTH_LONG).show();
    }

    class MyTimer extends TimerTask {
        Context context;

        public MyTimer(Context context) {
            this.context = context;
        }

        public void run() {
            //progressDialog.dismiss();
            Intent intent = new Intent(this.context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.context.startActivity(intent);
        }
    }
}
