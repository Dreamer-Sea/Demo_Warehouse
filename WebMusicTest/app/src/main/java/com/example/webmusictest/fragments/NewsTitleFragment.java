package com.example.webmusictest.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.webmusictest.R;
import com.example.webmusictest.adapters.NewsAdapter;
import com.example.webmusictest.beans.NewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by King on 2017/6/14.
 */

public class NewsTitleFragment  extends Fragment{

    private List<NewsItem> newsItemList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_fragment, container, false);
        RecyclerView newsTitleRecyclerView = (RecyclerView) view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter();
        newsTitleRecyclerView.setAdapter(adapter);
        return view;
    }

    private void initNews(){

    }
}
