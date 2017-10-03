package com.example.paezand.movieslist.http;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.paezand.movieslist.http.apimodel.TopMovies;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by paezand on 10/3/17.
 */

public class BaseApi implements IBaseApi {

    public static final String BASE_URL = "https://api.themoviedb.org/3/movie/";
    public static final String API_KEY = "084ff24f0e1753c6b3c68db419cee710";

    @Nullable
    private static BaseApi instance;

    @Nullable
    private static MovieClient client;

    @NonNull
    public static BaseApi getInstance() {
        synchronized (BaseApi.class) {
            if (instance == null) {
                instance = new BaseApi();
            }
        }
        return instance;
    }

    private OkHttpClient getConfiguredOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    private Retrofit getConfiguredRetrofitInstance() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .client(getConfiguredOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public BaseApi() {
        this.client = getConfiguredRetrofitInstance().create(MovieClient.class);
    }


    @Override
    public void getMovieList(@NonNull final Integer page,
                             @NonNull final Callback<TopMovies> callback) {
        client.getTopMoviesList(API_KEY, page.toString()).enqueue(callback);
    }
}
