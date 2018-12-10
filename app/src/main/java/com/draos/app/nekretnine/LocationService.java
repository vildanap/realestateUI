package com.draos.app.nekretnine;


import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LocationService {
    @GET("locations/all")
    Call<List<com.draos.app.nekretnine.Location>> all();

    @GET("locations/{locationId}")
    Call<Location> getLocation(@Path("locationId") long id);

    @POST("locations/new")
    Call<ResponseBody> postUser(@Body Location location);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}