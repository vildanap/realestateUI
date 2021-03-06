package com.draos.app.nekretnine;


import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LocationService {
    @GET("locations/all")
    Call<List<com.draos.app.nekretnine.Location>> all();

    @GET("locations/{locationId}")
    Call<Location> getLocation(@Path("locationId") long id);

    @POST("locations/new")
    Call<ResponseBody> createLocation(@Body Location location);

    @GET("files/{id}")
    Call<ResponseBody> getFile(@Path("id") long id);

    @PUT("locations/{locationId}")
    Call<ResponseBody> updateLocation(@Path("locationId") long id, @Body Location location);

    @Multipart
    @POST("/uploadFile")
    Call<ResponseBody> postImage(@Part MultipartBody.Part image);

  /*  public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.112" +
                    ":8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();*/
}