package com.example.webmusictest.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.webmusictest.R;
import com.example.webmusictest.beans.NewsItem;

import java.util.List;

/**
 * Created by King on 2017/6/14.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
    private List<NewsItem> mNewsList;
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView newsTitleText;
        TextView newsDayText;

        public ViewHolder(View itemView) {
            super(itemView);
            newsTitleText = (TextView) itemView.findViewById(R.id.news_title);
            newsDayText = (TextView) itemView.findViewById(R.id.news_day);
        }
    }

    public NewsAdapter(List<NewsItem> mNewsList) {
        this.mNewsList = mNewsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsItem newsItem = mNewsList.get(position);
        holder.newsTitleText.setText(newsItem.getTitle());
        holder.newsDayText.setText(newsItem.getDay());
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }
}
