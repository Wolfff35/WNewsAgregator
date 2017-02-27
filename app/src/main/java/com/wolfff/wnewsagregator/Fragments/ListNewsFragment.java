package com.wolfff.wnewsagregator.Fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.wolfff.wnewsagregator.Adapters.ListNewsAdapter;
import com.wolfff.wnewsagregator.Obj.WNews;
import com.wolfff.wnewsagregator.SQlite.DBConnector;

import java.util.ArrayList;

/**
 * Created by wolff on 21.10.2016.
 */

public class ListNewsFragment extends ListFragment {
    private ListNewsFragmentListener listener;

    public interface ListNewsFragmentListener {
        void onNewsSelected(long idNews,long idChannel);

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        long idChannel= this.getArguments().getLong("idChannel");
        Context context = getActivity().getApplicationContext();
        DBConnector dbConnector = new DBConnector(context);
        dbConnector.open();
        Cursor cursor = dbConnector.news_getUnreaded_cursor(idChannel);
        final ArrayList<WNews> news = news_getAll_ObjectArray(cursor);

        ListNewsAdapter listNewsAdapter= new ListNewsAdapter(context,news);
        setListAdapter(listNewsAdapter);

        getListView().setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listener.onNewsSelected(news.get(i).getId(),news.get(i).getIdChannel());
                Log.e("onItemClick","================================================================ i = "+i+" =      "+news.get(i).toString()+"; "+news.get(i).getLink());

            }
        });

    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (ListNewsFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
    public static ListNewsFragment newInstance(long idChannel){
        ListNewsFragment fragment = new ListNewsFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("idChannel", idChannel);
        fragment.setArguments(bundle);
        return fragment;
    }

    //==============================================================================================
//    @Override
//    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
        //View v = inflater.inflate(R.layout.list_news_fragment, null);
//        return v;
//    }

    public ArrayList<WNews> news_getAll_ObjectArray(Cursor cursor){
        ArrayList<WNews> data = new ArrayList<>(cursor.getCount());
        cursor.moveToFirst();
        DBConnector dbConnector = new DBConnector(getActivity().getApplicationContext());
        while (cursor.isAfterLast() == false) {
            WNews news = dbConnector.getNews(cursor);
           /* long id                 = cursor.getLong(cursor.getColumnIndex(KEY_ID));
            String guid        = cursor.getString(cursor.getColumnIndex(KEY_GUID));
            String pubDate         = cursor.getString(cursor.getColumnIndex(KEY_PUBDATE));
            String title    = cursor.getString(cursor.getColumnIndex(KEY_TITLE));
            String description     = cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION));
            String link       = cursor.getString(cursor.getColumnIndex(KEY_LINK));
            String enclosure       = cursor.getString(cursor.getColumnIndex(KEY_ENCLOSURE));
            int isRead       = cursor.getInt(cursor.getColumnIndex(KEY_ISRAD));
            long idChannel       = cursor.getLong(cursor.getColumnIndex("_idChannel"));
            long date       = cursor.getLong(cursor.getColumnIndex(KEY_DATE));
*/
            data.add(news);
            //WNews(long id, String guid, String pubDate, String title, String description, String link, String enclosure, int isRead, long idChannel, long date){
  //              data.add(new WNews(id,guid,pubDate,title,description,link,enclosure,isRead,idChannel,date));
            cursor.moveToNext();
        }
        return data;
    }

}
