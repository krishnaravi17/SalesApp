package com.tech.salesapp.Interface;

import com.google.gson.JsonObject;
import com.tech.salesapp.Entity.Login;
import com.tech.salesapp.Entity.RetroPhoto;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {


    @GET("/photos")
    Call<List<RetroPhoto>> getAllPhotos();

  /*  @POST("v1/Login")
    Call<JsonObject> LoginFunction(@Query("username") String username, @Query("password") String password);*/

    /*@POST("v1/auth/login")
    @FormUrlEncoded
    Call<JsonObject> LoginFunction(@FieldMap Map<String,String> params);*/

    @POST("v1/Login")
    Call<Login> LoginFunction(@Query("username") String username, @Query("password") String password);


    @GET("v1/products/list")
    Call<JsonObject> ProductData(@Query("merchant_id") String merchant_id,@Query("category_id") String category_id);

    @GET("v1/brands/list")
    Call<JsonObject> BrandData(@Query("merchant_id") String merchant_id);

    @GET("v1/categories/list")
    Call<JsonObject> CategoriesData(@Query("merchant_id") String merchant_id);

    @GET("v1/customersales/taxrate")
    Call<JsonObject> TaxData(@Query("tax_id") String tax_id);


}
