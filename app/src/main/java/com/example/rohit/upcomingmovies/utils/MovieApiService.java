package com.example.rohit.upcomingmovies.utils;

import com.example.rohit.upcomingmovies.model.JsonRequestDiscoverMovieResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rohit on 07/08/16.
 */
public interface MovieApiService {

    @GET("movie/upcoming")
    Call<JsonRequestDiscoverMovieResult> getDiscoverMovieFeed(
            @Query("api_key") String apiKey
    );

//    @GET("movie/{id}/videos")
//    Call<JsonRequestMovieVideoResult> getMovieVideoFeed(
//            @Path("id") String movieId,
//            @Query("api_key") String apiKey
//    );
}

