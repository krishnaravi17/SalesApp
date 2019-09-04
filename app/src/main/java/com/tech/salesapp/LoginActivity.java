package com.tech.salesapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.tech.salesapp.Entity.Login;
import com.tech.salesapp.Entity.ProductList;
import com.tech.salesapp.Interface.ApiService;
import com.tech.salesapp.Network.ApiClient;
import com.tech.salesapp.Utils.AndroidUtils;
import com.tech.salesapp.Utils.AppProgress;
import com.tech.salesapp.Utils.SharedDataPrefs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tech.salesapp.Interface.InterfaceForListdata.LSTProduct_List;


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
                //username.setText("");
                // password.setText("");
                if (username != null && username.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(LoginActivity.this, "Fill username first!!", Toast.LENGTH_SHORT).show();
                } else if (password != null && password.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(LoginActivity.this, "Fill password first!!", Toast.LENGTH_SHORT).show();
                } else {
                    sendDetailsToServer(username.getText().toString(), password.getText().toString());
                }


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

    private void sendDetailsToServer(final String username, final String password) {

        if (AndroidUtils.checkYourMobileDataConnection(LoginActivity.this)) {

            ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);

            final ProgressDialog dialog = AppProgress.showProgress(LoginActivity.this);
            // Call<Login> call = apiService.LoginFunction("merchant2", "123456");


            Map<String, String> params = new HashMap<String, String>();
            params.put("username", username);
            params.put("password", password);

            //Call<JsonObject> call = apiService.LoginFunction(username,password);
            Call<JsonObject> call = apiService.LoginFunction(params);

            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                    if (response != null) {
                        SharedPreferences shp = getSharedPreferences(SharedDataPrefs.Shared_Prefrence_Name, MODE_PRIVATE);

                        String UserID = shp.getString(SharedDataPrefs.UserID, "0");

                        Toast.makeText(LoginActivity.this, "Logged In!!", Toast.LENGTH_LONG).show();

                        if (response != null && response.body().get("message").toString().equalsIgnoreCase("\"Login is successfull!\"")) {


                            if (UserID.equalsIgnoreCase("0")) {

                                Login data = Login.getjsonObject(response.body().get("data").toString());

                                SharedPreferences.Editor editor = shp.edit();
                                editor.putString(SharedDataPrefs.UserID, data.getId());
                                editor.putString(SharedDataPrefs.FirstName, data.getFirstName());
                                editor.commit();

                                getProductList(data.getId());

                                //shp = context.getSharedPreferences(Name_Struct.Shared_Prefrence_Name, context.MODE_PRIVATE);


                            } else {

                                getProductList(UserID);//temporary change..later on i will put this is sharedprefs
                            }


                        } else {

                            Toast.makeText(LoginActivity.this, "Login with correct credentials!!", Toast.LENGTH_SHORT).show();
                            getProductList(UserID);//testing
                        }


                    }

                    AppProgress.hideProgress(dialog);
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    //getProductList();

                    AppProgress.hideProgress(dialog);
                }
            });


        } else {

            Toast.makeText(this, getResources().getString(R.string.Internet_Connection_Not_Available), Toast.LENGTH_SHORT).show();
        }

    }

    private void getProductList(String UserID) {

        if (AndroidUtils.checkYourMobileDataConnection(LoginActivity.this)) {

            ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);

            final ProgressDialog dialog = AppProgress.showProgress(LoginActivity.this);

            Call<JsonObject> call = apiService.ProductData("2","1");//merchant id

            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                    if (response != null && response.body().get("message").toString().equalsIgnoreCase("\"Request is successfull!\"")) {

                        List<ProductList> productLists = ProductList.getListDataFromJson(response.body().get("data").toString());

                        for (ProductList productList : productLists) {

                            LSTProduct_List.add(productList);

                        }

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                        Toast.makeText(LoginActivity.this, "Fetched Details!!", Toast.LENGTH_LONG).show();

                    } else {

                        Toast.makeText(LoginActivity.this, "Some thing went wrong!!", Toast.LENGTH_SHORT).show();
                    }

                    AppProgress.hideProgress(dialog);
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    AppProgress.hideProgress(dialog);
                }
            });


        } else {
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

/*/*  if (AndroidUtils.checkYourMobileDataConnection(this)) {
            ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
            //  final Dialog dialog=AppProgress.showProgress( this);
            final ProgressDialog dialog = AppProgress.showProgress(LoginActivity.this);
// Fetching all notes
            apiService.LoginFunction(username, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<Login>() {
                        @Override
                        public void onSuccess(Login baseResponse) {
                          //  boolean confirmRequired = baseResponse.getRequiredConfirm();
                            AppProgress.hideProgress(dialog);
                        }

                        @Override
                        public void onError(Throwable e) {
                            // Network error
                           // new SnackMsg(LoginActivity.this, e.getMessage());
                            AppProgress.hideProgress(dialog);
                        }
                    });
        } else {
            //new ShowAlert(LoginActivity.this, getResources().getString(R.string.Internet_Connection_Required));
        }*/
