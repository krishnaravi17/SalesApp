package com.tech.salesapp.Network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    private static Retrofit retrofit, retrofitTests = null;
    private static int REQUEST_TIMEOUT = 60;
    private static OkHttpClient okHttpClient, okHttpClientTest;
    /*private static final String BASE_URL = "https://jsonplaceholder.typicode.com";*/
    //private static final String BASE_URL = "https://api.learn2crack.com/android/";

    private static final String BASE_URL = "http://ciboapp.me/stockapi/api/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
