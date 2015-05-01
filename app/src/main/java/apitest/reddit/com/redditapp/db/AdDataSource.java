package apitest.reddit.com.redditapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import apitest.reddit.com.redditapp.models.NotificationDataBean;

/**
 * Created by divyanshunegi on 1/5/15.
 */

public class AdDataSource {

    private SQLiteDatabase sqLiteDatabase = null;
    private MySQLiteHelper mySQLiteHelper = null;
    private AdDataSourceListener adDataSourceListener = null;

    private String[] allColumns = { MySQLiteHelper.TablePosts.COLUMN_ID, MySQLiteHelper.TablePosts.COLUMN_POST_URL,
            MySQLiteHelper.TablePosts.COLUMN_POST_THUMBNAIL, MySQLiteHelper.TablePosts.COLUMN_POST_TITLE, MySQLiteHelper.TablePosts.COLUMN_POST_SUBREDDIT };

    public AdDataSource(Context context, AdDataSourceListener adDataSourceListener){
        mySQLiteHelper = new MySQLiteHelper(context);
        this.adDataSourceListener = adDataSourceListener;
        open();
    }

    public void open(){
        sqLiteDatabase = mySQLiteHelper.getWritableDatabase();
    }

    synchronized public void insertAd(NotificationDataBean notificationDataBean){

        ContentValues contentValues = getContentValues(notificationDataBean);
        long insert = sqLiteDatabase.insertWithOnConflict(MySQLiteHelper.TablePosts.TABLE_POSTS, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
        adDataSourceListener.onInsert(notificationDataBean);

    }


    private ContentValues getContentValues(NotificationDataBean notificationDataBean){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySQLiteHelper.TablePosts.COLUMN_ID, notificationDataBean.getId());
        contentValues.put(MySQLiteHelper.TablePosts.COLUMN_POST_URL, notificationDataBean.getTitle());
        contentValues.put(MySQLiteHelper.TablePosts.COLUMN_POST_THUMBNAIL, notificationDataBean.getThumbnail());
        contentValues.put(MySQLiteHelper.TablePosts.COLUMN_POST_TITLE, notificationDataBean.getUrl());
        contentValues.put(MySQLiteHelper.TablePosts.COLUMN_POST_SUBREDDIT, notificationDataBean.getSubreddit());
        return contentValues;
    }


//    public NotificationDataBean getAdById(String id){
//        Cursor cursor = sqLiteDatabase.query(MySQLiteHelper.TableAds.TABLE_ADS,
//                allColumns, MySQLiteHelper.TableAds.COLUMN_ID + "=" + id, null, null, null, null);
//        Ad ad = null;
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            //System.out.println("here");
//            ad = cursorToAd(cursor);
//            // Temp fix
//            break;
//
//        }
//        // make sure to close the cursor
//        cursor.close();
//        return ad;
//    }

    public void getAllPostsBeans(){

            adDataSourceListener.onGetPost(getAllPosts());

    }

    private NotificationDataBean getAllPosts(){

        Cursor cursor = sqLiteDatabase.query(MySQLiteHelper.TablePosts.TABLE_POSTS,
                allColumns, null, null, null, null, null);
        NotificationDataBean notificationDataBean = null;
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            //System.out.println("here");
            notificationDataBean = cursorToAd(cursor);
            // Temp fix
            break;

        }
        // make sure to close the cursor
        cursor.close();
        return notificationDataBean;
    }

    private NotificationDataBean cursorToAd(Cursor cursor) {

        NotificationDataBean notificationDataBean = new NotificationDataBean();
        notificationDataBean.setId(cursor.getString(0));
        notificationDataBean.setUrl(cursor.getString(1));
        notificationDataBean.setThumbnail(cursor.getString(2));
        notificationDataBean.setTitle(cursor.getString(3));
        notificationDataBean.setSubreddit(cursor.getString(4));
        return notificationDataBean;

    }

    public interface AdDataSourceListener {

        void onInsert(NotificationDataBean notificationDataBean);
        void onGetPost(NotificationDataBean notificationDataBean);

    }
}

