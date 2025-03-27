package com.heydar.simplemcv.model.network.retrofit;

import com.heydar.simplemcv.utils.AppSharedPref;
import com.heydar.simplemcv.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private final OkHttpClient httpClient;
    private static Retrofit retrofit = null;
    private final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

    public RetrofitService() {
        Interceptor interceptor = createInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        retrofit = createRetrofit().build();
    }
    private Retrofit.Builder createRetrofit() {
        return createRetrofit(GsonConverterFactory.create());
    }

    private Retrofit.Builder createRetrofit(Converter.Factory factory) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(httpClient)
                .addConverterFactory(factory);
    }

    public void setLoggingLevel(HttpLoggingInterceptor.Level level) {
        httpLoggingInterceptor.setLevel(level);
    }

    private Interceptor createInterceptor() {
        return chain -> {
            String token = AppSharedPref.getToken();
            Request request = chain.request().newBuilder()
                    .header("sessionToken", token)
                    .header("X-Parse-Application-Id", Constants.APPLICATION_ID)
                    .header("X-Parse-REST-API-Key", Constants.REST_API_KEY)
                    .header("Content-Type", "application/json")
                    .build();
            return chain.proceed(request);
        };
    }
}
