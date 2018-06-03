package centertableinc.ed.samplecontentproviders.data;

import android.content.ContentValues;

public class Movie {
    public static final String _ID = "_id";
    public static final String MOVIE_ID = "movie_id";
    public static final String MOVIE_NAME = "movie_name";

    public static final String PROJECTION[] = {
            _ID,
            MOVIE_ID,
            MOVIE_NAME
    };

    private String id;
    private String movieId;
    private String movieName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

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
