package com.example.webmusictest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.webmusictest.R;
import com.example.webmusictest.adapters.NewsAdapter;
import com.example.webmusictest.beans.NewsItem;
import com.example.webmusictest.threads.ReceiveXML;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by King on 2017/6/14.
 */

public class NewsTitleFragment  extends Fragment {

    private List<NewsItem> newsItemList = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_fragment, container, false);

        RecyclerView newsTitleRecyclerView = (RecyclerView) view.findViewById(R.id.news_title_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(layoutManager);


        try {
            ReceiveXML rx = new ReceiveXML();
            rx.start();
            rx.join();
            newsItemList = rx.getData();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        adapter = new NewsAdapter(newsItemList);
        newsTitleRecyclerView.setAdapter(adapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);

        //下拉刷新
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    ReceiveXML rx = new ReceiveXML();
                    rx.start();
                    rx.join();

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

}
