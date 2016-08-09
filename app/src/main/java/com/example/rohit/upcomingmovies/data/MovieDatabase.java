package com.example.rohit.upcomingmovies.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by rohit on 07/08/16.
 */

@Database(version = MovieDatabase.VERSION)
public class MovieDatabase {
    private MovieDatabase(){}

    public static final int VERSION = 1;

    @Table(MovieColumns.class) public static final String MOVIES = "movies";
}
