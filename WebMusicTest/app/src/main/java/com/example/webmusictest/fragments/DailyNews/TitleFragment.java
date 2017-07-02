package com.example.webmusictest.fragments.DailyNews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.webmusictest.R;
import com.example.webmusictest.adapters.DailyNewsDecoration;
import com.example.webmusictest.beans.DailyNews.NewsItem;
import com.example.webmusictest.threads.ReceiveXML;
import com.example.webmusictest.utils.ParseJsonWithGson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by King on 2017/6/14.
 */

public class TitleFragment extends Fragment {

    private List<NewsItem> newsItemList = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsAdapter adapter;
    private final String URL = "http://route.showapi.com/1071-1";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_fragment, container, false);

        RecyclerView newsTitleRecyclerView = (RecyclerView) view.findViewById(R.id.news_title_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(layoutManager);

        try {
            ReceiveXML rx = new ReceiveXML(URL);
            rx.start();
            rx.join();
            newsItemList = ParseJsonWithGson.parseDailyNewsJson(rx.getRes());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        adapter = new NewsAdapter(newsItemList);
        newsTitleRecyclerView.addItemDecoration(new DailyNewsDecoration(this.getActivity(), DailyNewsDecoration.VERTICAL_LIST));
        newsTitleRecyclerView.setAdapter(adapter);

        //下拉刷新
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.newstitle_swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    ReceiveXML rx = new ReceiveXML(URL);
                    rx.start();
                    rx.join();
                    newsItemList.addAll(ParseJsonWithGson.parseDailyNewsJson(rx.getRes()));
                    adapter.notifyDataSetChanged();
                    mSwipeRefreshLayout.setRefreshing(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //上拉加载更多
        newsTitleRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()){

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });

        return view;
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

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
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    NewsItem news = mNewsList.get(holder.getAdapterPosition());

                    //点击新闻标题，跳转到新闻的正文
//                    ContentActivity.actionStart(getActivity(), news.getUrl());
                    Context context = getActivity();
                    Intent intent = new Intent(context, ContentActivity.class);
                    intent.putExtra("Url", news.getUrl());
                    context.startActivity(intent);
                }
            });
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

}
