package centertableinc.ed.samplecontentproviders.data;

import android.content.Context;
import android.net.Uri;

public class ProviderUtil {
    public static final Uri MOVIE_PROVIDER_URI;
    static {
        Uri.Builder builder = new Uri.Builder();
        MOVIE_PROVIDER_URI = builder
                .scheme("content")
                .authority(MoviesProvider.AUTHORITY)
                .appendPath(MoviesProvider.MOVIES_DATA)
                .build();
    }

    public void addMovie(Context context, Movie movie){
        context.getContentResolver().insert(MOVIE_PROVIDER_URI, movie.getMovieContentValues());
    }

    public Movie getMovie(Context context){
        //TODO: fill here
        return null;
    }

    public void deleteMovie(Context context, Movie movie){
        context.getContentResolver().delete(MOVIE_PROVIDER_URI,
                getWhereClauseForContentQuery(Movie.MOVIE_ID),
                new String[]{movie.getMovieContentValues().get(Movie.MOVIE_ID).toString()});
    }

    private String getWhereClauseForContentQuery(String columnName){
        return columnName + "=?";
    }

}
