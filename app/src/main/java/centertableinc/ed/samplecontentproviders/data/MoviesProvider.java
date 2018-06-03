package centertableinc.ed.samplecontentproviders.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class MoviesProvider extends ContentProvider {
    //Authority must be a unique string within the Play Store
    public static final String AUTHORITY = "centertableinc.ed.samplecontentproviders";
    public static final String MOVIES_DATA = "movies";

    public static final Uri MOVIE_PROVIDER_URI;

    static {
        Uri.Builder builder = new Uri.Builder();
        MOVIE_PROVIDER_URI = builder
                .scheme("content")
                .authority(AUTHORITY)
                .appendPath(MOVIES_DATA)
                .build();
    }

    /*
    Currently we're going to supply 2 different URIs:
        1- Batch movies: content://centertableinc.ed.samplecontentproviders/movies
        2- Single movie: content://centertableinc.ed.samplecontentproviders/movies/#
     */
    private static final int MOVIES = 100;
    private static final int MOVIE = 101;

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AUTHORITY, MOVIES_DATA.toString(), MOVIES);
        uriMatcher.addURI(AUTHORITY, MOVIES_DATA + "/#", MOVIE);
    }

    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        MoviesDBHelper dbHelper = new MoviesDBHelper(getContext());

        db = dbHelper.getWritableDatabase();
        return (db != null);
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;

        switch(uriMatcher.match(uri)){
            case MOVIES:
                cursor = db.query(MoviesDBHelper.MOVIES_TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                /* if _id is equal to -1 insertion failed */
                if (cursor != null) {
                    /*
                     * This will help to broadcast that database has been changed,
                     * and will inform entities to perform automatic update.
                     */
                    try{
                        getContext().getContentResolver().notifyChange(uri, null);
                    }catch (NullPointerException ex){
                        Log.e(getClass().getName(), "query(), ContentResolver is null");
                    }
                }

                break;
            default:
                break;
        }

        Log.d(getClass().getName(), "query called");
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch(uriMatcher.match(uri)){
            case MOVIES:
                long _id = db.insert(MoviesDBHelper.MOVIES_TABLE_NAME, null, values);

                /* if _id is equal to -1 insertion failed */
                if (_id != -1) {
                    /*
                     * This will help to broadcast that database has been changed,
                     * and will inform entities to perform automatic update.
                     */
                    try{
                        getContext().getContentResolver().notifyChange(uri, null);
                    }catch (NullPointerException ex){
                        Log.e(getClass().getName(), "insert(), ContentResolver is null");
                    }
                }

                break;
            default:
                break;
        }

        Log.d(getClass().getName(), "insert called");
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;

        switch(uriMatcher.match(uri)){
            case MOVIES:
                count = db.delete(MoviesDBHelper.MOVIES_TABLE_NAME, selection, selectionArgs);

                if (count > 0) {
                    /*
                     * This will help to broadcast that database has been changed,
                     * and will inform entities to perform automatic update.
                     */
                    try{
                        getContext().getContentResolver().notifyChange(uri, null);
                    }catch (NullPointerException ex){
                        Log.e(getClass().getName(), "delete(), ContentResolver is null");
                    }
                }

                break;
            default:
                break;
        }

        Log.d(getClass().getName(), "delete called");
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
