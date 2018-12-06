package com.draos.app.nekretnine;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LocationService {
    @GET("locations/all")
    Call<List<com.draos.app.nekretnine.Location>> all();


}