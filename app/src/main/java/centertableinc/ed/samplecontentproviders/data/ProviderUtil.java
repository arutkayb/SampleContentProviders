package centertableinc.ed.samplecontentproviders.data;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import static centertableinc.ed.samplecontentproviders.data.MoviesProvider.MOVIE_PROVIDER_URI;

public class ProviderUtil {

    public void insertMovie(Context context, Movie movie){
        context.getContentResolver().insert(MOVIE_PROVIDER_URI, movie.getMovieContentValues());
    }

    public Movie getMovie(Context context, String movieId){
        Movie movie = null;

        Cursor cursor = context.getContentResolver().query(MOVIE_PROVIDER_URI,
                Movie.PROJECTION,
                getWhereClauseForContentQuery(Movie.MOVIE_ID),
                new String[]{movieId},
                null);

        if(cursor != null && cursor.moveToFirst())
        {
            String id = cursor.getString(cursor.getColumnIndex(Movie._ID));
            String movieName = cursor.getString(cursor.getColumnIndex(Movie.MOVIE_NAME));

            movie = new Movie(movieId, movieName);
            movie.setId(id);
        }

        return movie;
    }

    public void deleteMovie(Context context, Movie movie){
        deleteMovie(context, movie.getMovieContentValues().get(Movie.MOVIE_ID).toString());
    }

    public void deleteMovie(Context context, String movieId){
        context.getContentResolver().delete(MOVIE_PROVIDER_URI,
                getWhereClauseForContentQuery(Movie.MOVIE_ID),
                new String[]{movieId});
    }

    public static String getWhereClauseForContentQuery(String columnName){
        return columnName + "=?";
    }

}
