package com.example.rohit.upcomingmovies;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.rohit.upcomingmovies.data.MovieColumns;
import com.example.rohit.upcomingmovies.data.MovieProvider;
import com.example.rohit.upcomingmovies.model.Movie;
import com.example.rohit.upcomingmovies.service.MovieService;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private final int MOVIE_LOADER = 0;
    Cursor mCursor = null;
    private MovieAdapter movieAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, MovieService.MovieReceiver.class);
        intent.putExtra(MovieService.MSG_TAG, "Hello World - Movie Service");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,
                PendingIntent.FLAG_ONE_SHOT);
        AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+5000, pendingIntent);
        movieAdapter = new MovieAdapter(this,null,0);
        ListView listView = (ListView) findViewById(R.id.listview_movie);
        listView.setAdapter(movieAdapter);

        getSupportLoaderManager().restartLoader(MOVIE_LOADER,null,this);
//        Cursor cursor = getContentResolver().query(MovieProvider.Movies.Content_Uri,null,null,null,null);
        movieAdapter.swapCursor(mCursor);
    }

    @Override
    public void onStart(){
        super.onStart();
        getSupportLoaderManager().initLoader(MOVIE_LOADER,null,this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                this,
                MovieProvider.Movies.Content_Uri,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        movieAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
