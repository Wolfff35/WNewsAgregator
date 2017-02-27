package com.wolfff.wnewsagregator.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wolfff.wnewsagregator.Obj.WNews;
import com.wolfff.wnewsagregator.R;
import com.wolfff.wnewsagregator.Tools.ConvertValues;

import java.util.ArrayList;

/**
 * Created by wolff on 26.10.2016.
 */

public class ListNewsAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater lInflater;
    private ArrayList<WNews> news;

    public ListNewsAdapter(Context context, ArrayList<WNews> news) {
        this.context = context;
        this.news = news;
        this.lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public WNews getItem(int i) {
        return news.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View cView, ViewGroup viewGroup) {
        View view = cView;
        TextView tvTitle;
        TextView tvDescribe;
        TextView tvDate;
        //ImageView iwLogoNews;

        if(view==null){
            view = lInflater.inflate(R.layout.list_news_item_adapter,viewGroup,false);
        }
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvDescribe = (TextView) view.findViewById(R.id.tvDescribe);
        tvDate = (TextView) view.findViewById(R.id.tvDate);
        //iwLogoNews = (ImageView) view.findViewById(R.id.iwLogoNews);
        //tvTitle.setTextColor(getColor(R.color.colorPrimaryDark));
        WNews news = getItem(position);
        tvTitle.setText(news.getTitle());
        tvDescribe.setText(news.getDescription());
        String date = new ConvertValues().date_longToString(news.getDate(), "dd/MM/yy hh:mm");
        tvDate.setText(date);

        return view;
    }

}
