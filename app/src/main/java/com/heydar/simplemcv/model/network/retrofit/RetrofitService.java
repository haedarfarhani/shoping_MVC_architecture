package com.heydar.simplemcv.model.network.retrofit;

import android.util.Log;

import com.heydar.simplemcv.utils.AppSharedPref;
import com.heydar.simplemcv.utils.Constants;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static volatile RetrofitService instance;
    private final Retrofit retrofit;
    private final HttpLoggingInterceptor httpLoggingInterceptor;

    private RetrofitService() {
        httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        Interceptor interceptor = createInterceptor();

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    public static RetrofitService getInstance() {
        if (instance == null) {
            synchronized (RetrofitService.class) {
                if (instance == null) {
                    instance = new RetrofitService();
                }
            }
        }
        return instance;
    }

    public void setLoggingLevel(HttpLoggingInterceptor.Level level) {
        httpLoggingInterceptor.setLevel(level);
    }

    public <T> T createService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }

    private Interceptor createInterceptor() {
        return chain -> {
            String token = AppSharedPref.getToken() != null ? AppSharedPref.getToken() : "";
            Log.d("TAG234", "createInterceptor: " + token);
            Request request = chain.request().newBuilder()
                    .header("x-parse-session-token", token)
                    .header("X-Parse-Application-Id", Constants.APPLICATION_ID)
                    .header("X-Parse-REST-API-Key", Constants.REST_API_KEY)
                    .header("Content-Type", "application/json")
                    .build();
            return chain.proceed(request);
        };
    }
}

