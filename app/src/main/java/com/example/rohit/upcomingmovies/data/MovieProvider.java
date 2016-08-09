package com.example.rohit.upcomingmovies.data;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by rohit on 07/08/16.
 */

@ContentProvider(authority = MovieProvider.AUTHORITY, database = MovieDatabase.class)
public class MovieProvider {
    public static final String AUTHORITY = "com.example.rohit.upcomingmovies.data.MovieProvider";
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    interface Path {
        String MOVIES = "movies";
    }

    private static Uri buildUri(String ... paths){
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for(String path : paths){
            builder.appendPath(path);
        }
        return builder.build();
    }

    @TableEndpoint(table = MovieDatabase.MOVIES)
    public static class Movies {
        @ContentUri(
                path = Path.MOVIES,
                type = "vnd.android.cursor.dir/movie"
        )
        public static final Uri Content_Uri = buildUri(Path.MOVIES);

        @InexactContentUri(
                name = "MOVIE_ID",
                path = Path.MOVIES + "/*",
                type = "vnd.android.cursor.item/movie",
                whereColumn = MovieColumns.MOVIE_ID,
                pathSegment = 1
        )
        public static final Uri withId(String movie_id) {
            return buildUri(Path.MOVIES, movie_id);
        }
    }
}
