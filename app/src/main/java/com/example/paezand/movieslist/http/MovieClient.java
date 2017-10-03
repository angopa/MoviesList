package com.example.paezand.movieslist.http;

import com.example.paezand.movieslist.http.apimodel.TopMovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by paezand on 10/3/17.
 */

public interface MovieClient {

    @GET("top_rated")
    Call<TopMovies> getTopMoviesList(@Query("api_key") String apiKey,
                                     @Query("page") String page);

}
