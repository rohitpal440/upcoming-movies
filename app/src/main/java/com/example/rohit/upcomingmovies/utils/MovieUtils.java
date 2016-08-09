package com.example.rohit.upcomingmovies.utils;

import android.content.ContentProviderOperation;
import android.util.Log;

import com.example.rohit.upcomingmovies.data.MovieColumns;
import com.example.rohit.upcomingmovies.data.MovieProvider;
import com.example.rohit.upcomingmovies.data.generated.MovieDatabase;
import com.example.rohit.upcomingmovies.model.Movie;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by rohit on 07/08/16.
 */
public class MovieUtils {
    public static final String BASE_URL="http://api.themoviedb.org/3/";
    public static final String BASE_URL_IMAGE= "http://image.tmdb.org/t/p/";

    public static final Retrofit retrofitInstance;
    static {
        retrofitInstance = new Retrofit.Builder()
                .baseUrl(MovieUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ArrayList movieToContentVals(List<Movie> movies){
        ArrayList<ContentProviderOperation> batchOperations = new ArrayList<>();
        for(Movie movie:movies){
            batchOperations.add(buildMovieBatchOperation(movie));
        }
        return  batchOperations;
    }

    public static ContentProviderOperation buildMovieBatchOperation(Movie movie){
        ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(
                MovieProvider.Movies.Content_Uri);
        builder.withValue(MovieColumns.MOVIE_ID, Long.toString(movie.getId()));
        builder.withValue(MovieColumns.TITLE, movie.getTitle());
        builder.withValue(MovieColumns.LANG, movie.getOriginalLanguage());
        builder.withValue(MovieColumns.OVERVIEW, movie.getOverview());
        if(movie.getBackdropPath() != null){
            builder.withValue(MovieColumns.BACKDROP_PATH, movie.getBackdropPath());
        }else {
            builder.withValue(MovieColumns.BACKDROP_PATH, "NA");
        }
        if(movie.getPosterPath() != null){
            builder.withValue(MovieColumns.POSTER_PATH, movie.getPosterPath());
        }else {
            builder.withValue(MovieColumns.POSTER_PATH, "NA");
        }
        builder.withValue(MovieColumns.RELEASE_DATE,movie.getReleaseDate());
        builder.withValue(MovieColumns.POPULARITY, Double.toString(movie.getPopularity()));
        return builder.build();
    }
}
