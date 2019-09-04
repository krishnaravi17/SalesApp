package com.tech.salesapp.Interface;

import com.tech.salesapp.Entity.RetroPhoto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {


    @GET("/photos")
    Call<List<RetroPhoto>> getAllPhotos();


}
