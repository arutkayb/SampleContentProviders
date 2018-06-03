package centertableinc.ed.samplecontentproviders.data;

import android.content.ContentValues;

public class Movie {
    public static final String _ID = "_id";
    public static final String MOVIE_ID = "movie_id";
    public static final String MOVIE_NAME = "movie_name";

    private String movieId;
    private String movieName;

    public Movie(String movieId, String movieName)
    {
        this.movieId = movieId;
        this.movieName = movieName;
    }

    public ContentValues getMovieContentValues()
    {
        ContentValues values = new ContentValues();

        values.put(MOVIE_ID, movieId);
        values.put(MOVIE_NAME, movieName);

        return values;
    }
}
