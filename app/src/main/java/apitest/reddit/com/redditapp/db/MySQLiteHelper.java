package apitest.reddit.com.redditapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by divyanshunegi on 1/5/15.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "db_reddit";
    private static final int DB_VERSION = 1;

    interface TablePosts {

        public static final String TABLE_POSTS = "tbl_posts";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_POST_URL = "post_url";
        public static final String COLUMN_POST_THUMBNAIL = "post_thumbnail";
        public static final String COLUMN_POST_TITLE = "post_title";
        public static final String COLUMN_POST_SUBREDDIT = "post_subreddit";

        public static final String CREATE_QUERY = "CREATE TABLE " + TABLE_POSTS + "("
                + COLUMN_ID + " TEXT PRIMARY KEY, "
                + COLUMN_POST_URL + " TEXT NOT NULL, "
                + COLUMN_POST_THUMBNAIL + " TEXT NOT NULL, "
                + COLUMN_POST_TITLE + " TEXT NOT NULL, "
                + COLUMN_POST_SUBREDDIT + " TEXT NOT NULL);";
    }

    public MySQLiteHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TablePosts.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TablePosts.TABLE_POSTS);
        onCreate(sqLiteDatabase);

    }
}
