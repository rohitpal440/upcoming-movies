package com.example.rohit.upcomingmovies.data;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

/**
 * Created by rohit on 07/08/16.
 */
public class MovieColumns {
    @DataType(DataType.Type.INTEGER) @PrimaryKey @AutoIncrement
    public static final String _ID = "_id";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String MOVIE_ID = "movie_id";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String TITLE = "title";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String POPULARITY = "pupularity";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String RELEASE_DATE = "release_date";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String OVERVIEW = "overview";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String LANG = "lang";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String POSTER_PATH = "poster_path";
    @DataType(DataType.Type.TEXT) @NotNull
    public static final String BACKDROP_PATH = "backdrop_path";
}
