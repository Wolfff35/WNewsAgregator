package com.wolfff.wnewsagregator.Tools;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;

import com.wolfff.wnewsagregator.Obj.WNews;
import com.wolfff.wnewsagregator.RSS.RSSReader;
import com.wolfff.wnewsagregator.SQlite.DBConnector;

import java.util.ArrayList;

import static com.wolfff.wnewsagregator.SQlite.DBHelper.KEY_LINK;

/**
 * Created by wolff on 22.10.2016.
 */

public class GetNewsTask extends AsyncTask<Object, Void, Void> {


    @Override
    protected Void doInBackground(Object... strings) {

        Context context = (Context)strings[0];
        long idChannel = Long.parseLong(strings[1].toString());

        DBConnector dbConnector = new DBConnector(context);
        dbConnector.open();
        //ArrayList<WNews> nn = RSSReader.getRssNews("http://fishki.net/rss",idChannel);
        Cursor cc = dbConnector.channel_getById_cursor(idChannel);
        cc.moveToFirst();
        String url = cc.getString(cc.getColumnIndex(KEY_LINK));
        Log.e("GETNEWS",""+url);
        ArrayList<WNews> nn = RSSReader.getRssNews(url,idChannel);
        for (WNews n : nn) {
            dbConnector.addNewsToBD(n);
            String dateText = new ConvertValues().date_longToString(n.getDate(), "dd/MM/yy hh:mm");
             //Log.e("NEWS","guid: "+n.getGuid()+"\n pubdate: "+n.getPubDate()+"\n title: "+n.getTitle()+"\n desc: "+n.getDescription()+"\n link: "+n.getLink()+"\n date: "+dateText+"\n enclosure: "+n.getEnclosure());
            // Log.e("NEWS","==========================================================================");

        }
        dbConnector.close();
        dbConnector.open();
        dbConnector.close();

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
