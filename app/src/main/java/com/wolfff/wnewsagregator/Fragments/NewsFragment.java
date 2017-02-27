package com.wolfff.wnewsagregator.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wolfff.wnewsagregator.Obj.WNews;
import com.wolfff.wnewsagregator.R;
import com.wolfff.wnewsagregator.SQlite.DBConnector;

/**
 * Created by wolff on 21.10.2016.
 */

public class NewsFragment extends Fragment {
//    private ListNewsFragmentListener listener;

//    public interface ListNewsFragmentListener {
//        void onNewsSelected(long idNews,long idChannel);

//    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*
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
                listener.onNewsSelected(news.get(i));
                Log.e("onItemClick","================================================================ i = "+i+" =      "+news.get(i).toString()+"; "+news.get(i).getLink());

            }
        });
*/
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
/*        try {
            listener = (ListNewsFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
  */
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        listener = null;
    }
    public static NewsFragment newInstance(long idNews,long idChannel){
        NewsFragment fragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("idChannel", idChannel);
        bundle.putLong("idNews", idNews);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment,container, false);
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvDescribe = (TextView) view.findViewById(R.id.tvDescribe);
        ImageView ivLogo = (ImageView) view.findViewById(R.id.ivLogo);

        //long idChannel= this.getArguments().getLong("idChannel");
        long idNews= this.getArguments().getLong("idNews");
        DBConnector dbConnector = new DBConnector(getActivity().getApplicationContext());
        dbConnector.open();
        Cursor cursor = dbConnector.news_getById_cursor(idNews);
        Log.e("CURR","count = "+cursor.getCount());
        cursor.moveToFirst();
        WNews news = dbConnector.getNews(cursor);
        tvTitle.setText(news.getTitle());
        Log.e("DESC",""+news.getDescription());
        //String desc= news.getDescription().replaceAll("\\p{Cntrl}", "");
        //tvDescribe.setText(desc);
        dbConnector.close();
        return view;
    }
}
