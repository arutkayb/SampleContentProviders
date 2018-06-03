package centertableinc.ed.samplecontentproviders.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MoviesDBHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "movie_db";
    static final String MOVIES_TABLE_NAME = "movies";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + MOVIES_TABLE_NAME +
                    " (" +
                    Movie._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Movie.MOVIE_ID + " TEXT NOT NULL, " +
                    Movie.MOVIE_NAME + " TEXT NOT NULL " +
                    ");";

    public MoviesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MOVIES_TABLE_NAME);
        onCreate(db);
    }
}
