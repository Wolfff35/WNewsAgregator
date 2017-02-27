package com.wolfff.wnewsagregator.Tools;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.SubMenu;

import com.wolfff.wnewsagregator.Obj.WChannel;
import com.wolfff.wnewsagregator.SQlite.DBConnector;

import static com.wolfff.wnewsagregator.SQlite.DBHelper.KEY_ID;
import static com.wolfff.wnewsagregator.SQlite.DBHelper.KEY_TITLE;

/**
 * Created by wolff on 22.10.2016.
 */

public class Tools {
    public void updateAllNews(Context context){
        DBConnector dbConnector = new DBConnector(context);
        dbConnector.open();
        Cursor allNews = dbConnector.channel_getAll_cursor();
        allNews.moveToFirst();
        while (allNews.isAfterLast() == false) {
            String idChannel = allNews.getString(allNews.getColumnIndex(KEY_ID));
                Log.e("UPDATE","channel id = "+idChannel);
                GetNewsTask job = new GetNewsTask();
                job.execute(context, idChannel);
            allNews.moveToNext();
        }
        dbConnector.close();
    }
public void makeMainMenu(Context context,Menu menu){
    SubMenu sub_nav;
    DBConnector dbConnector = new DBConnector(context);
    dbConnector.open();
    Cursor groups = dbConnector.groups_getAll_cursor();
    groups.moveToFirst();
    int counter = 1;
    while (!groups.isAfterLast()){
        sub_nav= menu.addSubMenu(counter,groups.getInt(groups.getColumnIndex(KEY_ID)),counter,groups.getString(groups.getColumnIndex(KEY_TITLE)));
        Cursor channels = dbConnector.channel_getByGroup_cursor(groups.getInt(groups.getColumnIndex(KEY_ID)));
        int counter_Ch=1;
        channels.moveToFirst();
        while (!channels.isAfterLast()){

            sub_nav.add(counter,channels.getInt(channels.getColumnIndex(KEY_ID)),counter_Ch,channels.getString(channels.getColumnIndex(KEY_TITLE)));
            channels.moveToNext();
            counter_Ch++;
        }
        counter++;
        groups.moveToNext();
    }
 dbConnector.close();
};
//    public void showNews(Context context, long idChannel){
//    }
    public void test_fillData(Context context){
        DBConnector db =  new DBConnector(context);
//        db.group_insert("Развлечения");
//        WChannel channel = new WChannel(0,"Фишки.нет","http://fishki.net/rss/",1,"","","");
//        db.channel_insert(channel);
        db.group_insert("Новости");
        WChannel channel = new WChannel(0,"Лига","http://news.liga.net/all/rss.xml",2,"","","");
        db.channel_insert(channel);

        /*       dbConnector.group_insert("Группа 1");
        dbConnector.group_insert("Группа 2");
        dbConnector.group_insert("Группа 3");
        dbConnector.group_insert("Группа 4");
        WChannel channel = new WChannel(0,"TITLE","http://fishki.net/rss/",1,"","","");
        dbConnector.channel_insert(channel);

        WChannel channel2 = new WChannel(0,"df;gldfg'sgks'd;lkfd ","http://fishki.net/rss/",2,"","","");
        dbConnector.channel_insert(channel2);
*/
    }
}
    //boolean hasMessenger    = (cursor.getInt(cursor.getColumnIndex("_hasMessenger"))==1);
