package com.wolfff.wnewsagregator.SQlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wolff on 21.10.2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "news.db";
    public static final int DATABASE_VERSION = 1;
    public static final String KEY_ID = "_id";
    public static final String KEY_TITLE = "_title";
    public static final String KEY_PUBDATE = "_pubdate";
    public static final String KEY_DATE = "_date";
    public static final String KEY_DESCRIPTION = "_description";
    public static final String KEY_GUID= "_guid";
    public static final String KEY_LINK= "_link";
    public static final String KEY_ENCLOSURE= "_enclosure";
    public static final String KEY_IMAGEPATH= "_imagepath";

    public static final String KEY_ISRAD= "_isread";

    public static final String TABLE_NEWS = "news";
    public static final String TABLE_CHANNELS = "news_channels";
    public static final String TABLE_GROUPS = "news_groups";


    private static final String CREATE_TABLE_CHANNELS = "CREATE TABLE "+TABLE_CHANNELS+" ("+KEY_ID+" INTEGER PRIMARY KEY, "
            +KEY_TITLE+" TEXT, "
            +KEY_LINK+" TEXT, "
            +KEY_DESCRIPTION+" TEXT,"
            +KEY_PUBDATE+" TEXT,"
            +KEY_IMAGEPATH+" TEXT,"
            +"_idGroup INTEGER)";

    private static final String CREATE_TABLE_NEWS = "CREATE TABLE "+TABLE_NEWS+" ("+KEY_ID+" INTEGER PRIMARY KEY, "
            +KEY_GUID+" TEXT, "
            +KEY_PUBDATE+" TEXT,"
            +KEY_DATE+" INTEGER,"
            +KEY_TITLE+" TEXT, "
            +KEY_DESCRIPTION+" TEXT,"
            +KEY_LINK+" TEXT,"
            +KEY_ENCLOSURE+" TEXT,"
            +KEY_ISRAD+" INTEGER,"
            +"_idChannel INTEGER)";
    private static final String CREATE_TABLE_GROUPS = "CREATE TABLE "+TABLE_GROUPS+" ("+KEY_ID+" INTEGER PRIMARY KEY, "
            +KEY_TITLE+" TEXT)";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CHANNELS);
        db.execSQL(CREATE_TABLE_NEWS);
        db.execSQL(CREATE_TABLE_GROUPS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
