package com.wolfff.wnewsagregator.SQlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.wolfff.wnewsagregator.Obj.WChannel;
import com.wolfff.wnewsagregator.Obj.WNews;

import static com.wolfff.wnewsagregator.SQlite.DBHelper.KEY_DATE;
import static com.wolfff.wnewsagregator.SQlite.DBHelper.KEY_DESCRIPTION;
import static com.wolfff.wnewsagregator.SQlite.DBHelper.KEY_ENCLOSURE;
import static com.wolfff.wnewsagregator.SQlite.DBHelper.KEY_GUID;
import static com.wolfff.wnewsagregator.SQlite.DBHelper.KEY_ID;
import static com.wolfff.wnewsagregator.SQlite.DBHelper.KEY_IMAGEPATH;
import static com.wolfff.wnewsagregator.SQlite.DBHelper.KEY_ISRAD;
import static com.wolfff.wnewsagregator.SQlite.DBHelper.KEY_LINK;
import static com.wolfff.wnewsagregator.SQlite.DBHelper.KEY_PUBDATE;
import static com.wolfff.wnewsagregator.SQlite.DBHelper.KEY_TITLE;
import static com.wolfff.wnewsagregator.SQlite.DBHelper.TABLE_CHANNELS;
import static com.wolfff.wnewsagregator.SQlite.DBHelper.TABLE_GROUPS;
import static com.wolfff.wnewsagregator.SQlite.DBHelper.TABLE_NEWS;

/**
 * Created by wolff on 21.10.2016.
 */

public class DBConnector {
    private SQLiteDatabase database; // Для взаимодействия с базой данных
    private DBHelper databaseHelper;
    public DBConnector(Context context){
        databaseHelper = new DBHelper(context);
    }
    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }
    public void close(){
        if (database != null)
            database.close();
    }
    //==================================================================================================
     public Cursor news_getAll_cursor(long idChannel){
        String[] columns = new String[] {KEY_ID,KEY_GUID,KEY_PUBDATE,KEY_DATE,KEY_TITLE,KEY_DESCRIPTION,KEY_LINK,KEY_ENCLOSURE,KEY_ISRAD,"_idChannel"};
        String selection = "_idChannel = ?";
        String[] selectionArgs = new String[]{Long.toString(idChannel)};
        String groupBy = null;
        String having = null;
        String orderBy = KEY_ISRAD+","+KEY_DATE+" DESC";
        Cursor curr = database.query(TABLE_NEWS, columns,selection, selectionArgs, groupBy, having,orderBy);
        return curr;

    }
    public Cursor news_getUnreaded_cursor(long idChannel){
        String[] columns = new String[] {KEY_ID,KEY_GUID,KEY_PUBDATE,KEY_DATE,KEY_TITLE,KEY_DESCRIPTION,KEY_LINK,KEY_ENCLOSURE,KEY_ISRAD,"_idChannel"};
        String selection = KEY_ISRAD+" = ? AND _idChannel = ?";
        String[] selectionArgs = new String[]{"0",Long.toString(idChannel)};
        String groupBy = null;
        String having = null;
        String orderBy = KEY_PUBDATE+" DESC";
        Cursor curr = database.query(TABLE_NEWS, columns,selection, selectionArgs, groupBy, having,orderBy);
        return curr;

    }
    public void news_markRead(String guid){
        ContentValues news = new ContentValues();
        String whereClause = KEY_GUID+" = ?";
        String[] whereArgs =new String[]{guid};
        database.update(TABLE_NEWS,news,whereClause,whereArgs);
    }
    public Cursor news_getByGuid_cursor(String guid){
        String[] columns = new String[] {KEY_ID,KEY_GUID,KEY_PUBDATE,KEY_DATE,KEY_TITLE,KEY_DESCRIPTION,KEY_LINK,KEY_ENCLOSURE,KEY_ISRAD,"_idChannel"};
        String selection = ""+KEY_GUID+"=?";
        String[] selectionArgs = new String[]{guid};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        Cursor curr = database.query(TABLE_NEWS, columns,selection, selectionArgs, groupBy, having,orderBy);
        return curr;
    }
    public Cursor news_getById_cursor(long idNews){
        String[] columns = new String[] {KEY_ID,KEY_GUID,KEY_PUBDATE,KEY_DATE,KEY_TITLE,KEY_DESCRIPTION,KEY_LINK,KEY_ENCLOSURE,KEY_ISRAD,"_idChannel"};
        String selection = ""+KEY_ID+"=?";
        String[] selectionArgs = new String[]{Long.toString(idNews)};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        Cursor curr = database.query(TABLE_NEWS, columns,selection, selectionArgs, groupBy, having,orderBy);
        return curr;
    }

    public long news_insert(WNews news){
        ContentValues newNews = new ContentValues();
        newNews.put(KEY_GUID,news.getGuid());
        newNews.put(KEY_PUBDATE,news.getPubDate());
        newNews.put(KEY_DATE,news.getDate());
        newNews.put(KEY_TITLE,news.getTitle());
        newNews.put(KEY_DESCRIPTION,news.getDescription());
        newNews.put(KEY_LINK,news.getLink());
        newNews.put(KEY_ENCLOSURE,news.getEnclosure());
        newNews.put(KEY_ISRAD,news.getIsRead());
        newNews.put("_idChannel",news.getIdChannel());

        open(); // Открытие базы данных
        long rowID = database.insert(TABLE_NEWS, null, newNews);
        close(); // Закрытие базы данных
        return rowID;
    }

    public void news_deleteByGuid(String guid){
        open();
        database.delete(TABLE_NEWS,""+KEY_GUID+"="+guid,null);
        close();
    }
    //==================================================================================================
    public Cursor channel_getAll_cursor(){
        String[] columns = new String[] {KEY_ID,KEY_PUBDATE,KEY_TITLE,KEY_DESCRIPTION,KEY_LINK,KEY_IMAGEPATH,"_idGroup"};
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = KEY_PUBDATE+" DESC";
        Cursor curr = database.query(TABLE_CHANNELS, columns,selection, selectionArgs, groupBy, having,orderBy);
        return curr;

    }
    public Cursor channel_getById_cursor(long id){
        String[] columns = new String[] {KEY_ID,KEY_PUBDATE,KEY_TITLE,KEY_DESCRIPTION,KEY_LINK,KEY_IMAGEPATH,"_idGroup"};
        String selection = ""+KEY_ID+"=?";
        String[] selectionArgs = new String[]{Long.toString(id)};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        Cursor curr = database.query(TABLE_CHANNELS, columns,selection, selectionArgs, groupBy, having,orderBy);
        return curr;
    }
    public Cursor channel_getByGroup_cursor(long id){
        String[] columns = new String[] {KEY_ID,KEY_PUBDATE,KEY_TITLE,KEY_DESCRIPTION,KEY_LINK,KEY_IMAGEPATH,"_idGroup"};
        String selection = "_idGroup=?";
        String[] selectionArgs = new String[]{Long.toString(id)};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        Cursor curr = database.query(TABLE_CHANNELS, columns,selection, selectionArgs, groupBy, having,orderBy);
        return curr;
    }

    public long channel_insert(WChannel channel){
        ContentValues newChannel = new ContentValues();
        //newChannel.put(KEY_ID,channel.getId());
        newChannel.put(KEY_PUBDATE,channel.getPubDate());
        newChannel.put(KEY_TITLE,channel.getTitle());
        newChannel.put(KEY_DESCRIPTION,channel.getDescription());
        newChannel.put(KEY_LINK,channel.getLink());
        newChannel.put(KEY_IMAGEPATH,channel.getImage());
        newChannel.put("_idGroup",channel.getIdGroup());


        open(); // Открытие базы данных
        long rowID = database.insert(TABLE_CHANNELS, null, newChannel);
        close(); // Закрытие базы данных
        return rowID;
    }

    public void channel_deleteById(long id){
        open();
        database.delete(TABLE_CHANNELS,""+KEY_ID+"="+id,null);
        close();
    }
    public Cursor groups_getAll_cursor(){
        String[] columns = new String[] {KEY_ID,KEY_TITLE};
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        Cursor curr = database.query(TABLE_GROUPS, columns,selection, selectionArgs, groupBy, having,orderBy);
        return curr;

    }
    public long group_insert(String name){
        ContentValues newGroup = new ContentValues();
        newGroup.put(KEY_TITLE,name);

        open(); // Открытие базы данных
        long rowID = database.insert(TABLE_GROUPS, null, newGroup);
        close(); // Закрытие базы данных
        return rowID;
    }

    public void group_deleteById(long id){
        open();
        database.delete(TABLE_GROUPS,""+KEY_ID+"="+id,null);
        close();
    }

    //==================================================================================================
    public void addNewsToBD(WNews news){
        open();
        Cursor newsExist = news_getByGuid_cursor(news.getGuid());
        if(newsExist.getCount()==0){
            news_insert(news);
        }
        close();
    }
public WNews getNews(Cursor cursor){
    WNews news;
        long id                 = cursor.getLong(cursor.getColumnIndex(KEY_ID));
        String guid        = cursor.getString(cursor.getColumnIndex(KEY_GUID));
        String pubDate         = cursor.getString(cursor.getColumnIndex(KEY_PUBDATE));
        String title    = cursor.getString(cursor.getColumnIndex(KEY_TITLE));
        String description     = cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION));
        String link       = cursor.getString(cursor.getColumnIndex(KEY_LINK));
        String enclosure       = cursor.getString(cursor.getColumnIndex(KEY_ENCLOSURE));
        int isRead       = cursor.getInt(cursor.getColumnIndex(KEY_ISRAD));
        long idChannel       = cursor.getLong(cursor.getColumnIndex("_idChannel"));
        long date       = cursor.getLong(cursor.getColumnIndex(KEY_DATE));
        news = new WNews(id,guid,pubDate,title,description,link,enclosure,isRead,idChannel,date);
    return news;
}
}
