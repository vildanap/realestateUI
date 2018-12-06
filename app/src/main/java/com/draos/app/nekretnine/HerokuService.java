package com.draos.app.nekretnine;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HerokuService {
    @GET("locations/all")
    Call<ResponseBody> hello();
}