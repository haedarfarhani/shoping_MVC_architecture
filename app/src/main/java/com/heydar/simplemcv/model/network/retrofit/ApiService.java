package com.heydar.simplemcv.model.network.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("users/{id}")
    Call<Object> getUserById();
}
