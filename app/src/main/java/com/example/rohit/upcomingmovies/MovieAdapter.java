package com.example.rohit.upcomingmovies;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.rohit.upcomingmovies.data.MovieColumns;

/**
 * Created by rohit on 08/08/16.
 */
public class MovieAdapter extends CursorAdapter {

    public MovieAdapter(Context context, Cursor cursor, int flags){
        super(context,cursor,flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);

        return view;
    }

    /*
        This is where we fill-in the views with the contents of the cursor.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // our view is pretty simple here --- just a text view
        // we'll keep the UI functional with a simple (and slow!) binding.

        TextView tv = (TextView)view;
        tv.setText(cursor.getString(cursor.getColumnIndex(MovieColumns.TITLE)));
    }
}
