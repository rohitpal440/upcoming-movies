package com.example.rohit.upcomingmovies.service;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.os.RemoteException;
import android.util.Log;

import com.example.rohit.upcomingmovies.data.MovieProvider;
import com.example.rohit.upcomingmovies.model.JsonRequestDiscoverMovieResult;
import com.example.rohit.upcomingmovies.model.Movie;
import com.example.rohit.upcomingmovies.utils.MovieApiService;
import com.example.rohit.upcomingmovies.utils.MovieUtils;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

/**
 * Created by rohit on 07/08/16.
 */
public class MovieService extends IntentService {
    private final String LOG_TAG = MovieService.class.getSimpleName();
    public static final String MSG_TAG = "message_tag";
    public MovieService(){
        super("MovieService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(LOG_TAG,intent.getStringExtra(MSG_TAG));
        Log.i(LOG_TAG,"Service is called");
        Cursor cursor = getContentResolver().query(MovieProvider.Movies.Content_Uri,null,null,null,null);
        if (cursor.getCount() == 0 || cursor == null){
            Log.i(LOG_TAG, "Cursor is null or empty");
            MovieApiService moviesApiService = MovieUtils.retrofitInstance.create(MovieApiService.class);
            Call<JsonRequestDiscoverMovieResult> call = moviesApiService.getDiscoverMovieFeed("490cd18cb4fc35a484f6ac8b29eac1a4");

            try {
                JsonRequestDiscoverMovieResult response = call.execute().body();
                Log.i(LOG_TAG,"No. of results fetched " + response.getTotalResults());
                List<Movie> movies = response.getResults();
                getContentResolver().applyBatch(MovieProvider.AUTHORITY,MovieUtils.movieToContentVals(movies));

            } catch (RemoteException | OperationApplicationException | IOException e) {
                Log.e(LOG_TAG,e.getMessage());
            }
        }else {
            Log.i(LOG_TAG,"Data already available in cursor");
        }

    }

    static public class MovieReceiver extends BroadcastReceiver {
        private final String LOG_TAG = MovieReceiver.class.getSimpleName();
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(LOG_TAG,"Receiver: only for demonstration purpose");
            Intent sendIntent = new Intent(context,MovieService.class);
            sendIntent.putExtra(MSG_TAG, intent.getStringExtra(MSG_TAG));
            context.startService(sendIntent);
        }
    }
}
