package com.tech.salesapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tech.salesapp.Entity.RetroPhoto;
import com.tech.salesapp.Interface.ApiService;
import com.tech.salesapp.Network.ApiClient;
import com.tech.salesapp.Utils.AndroidUtils;
import com.tech.salesapp.Utils.AppProgress;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnSubmit;
    EditText username, password;
    TextView tv_eye;
    int countforEye = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorGray, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorGray));
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText("");
                password.setText("");
                sendDetailsToServer();
            }
        });
        tv_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countforEye == 0) {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    tv_eye.setBackground(getResources().getDrawable(R.drawable.ic_eye_off));
                    countforEye++;
                } else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    tv_eye.setBackground(getResources().getDrawable(R.drawable.ic_pass_eye));
                    countforEye--;
                }
            }
        });


    }

    private void sendDetailsToServer() {

        if (AndroidUtils.checkYourMobileDataConnection(LoginActivity.this)) {

            ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);

            final ProgressDialog dialog = AppProgress.showProgress(LoginActivity.this);
            Call<List<RetroPhoto>> call = apiService.getAllPhotos();

            call.enqueue(new Callback<List<RetroPhoto>>() {
                @Override
                public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {

                    if (response != null) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Logged In!!", Toast.LENGTH_LONG).show();

                    }

                    AppProgress.hideProgress(dialog);
                }

                @Override
                public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {


                    AppProgress.hideProgress(dialog);
                }
            });


        } else {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            Toast.makeText(this, getResources().getString(R.string.Internet_Connection_Not_Available), Toast.LENGTH_SHORT).show();
        }


    }

    private void findViewById() {
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        tv_eye = (TextView) findViewById(R.id.tv_eye);
    }
}
