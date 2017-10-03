package com.example.paezand.movieslist.http;

import android.support.annotation.NonNull;

import com.example.paezand.movieslist.http.apimodel.TopMovies;

import retrofit2.Callback;

/**
 * Created by paezand on 10/3/17.
 */

public interface IBaseApi {

    void getMovieList(@NonNull Integer page,
                      @NonNull Callback<TopMovies> callback);
}
