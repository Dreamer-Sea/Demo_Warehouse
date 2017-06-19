package com.example.webmusictest.utils;

import android.util.Log;

import com.example.webmusictest.beans.DailyNews;
import com.example.webmusictest.beans.NewsItem;
import com.example.webmusictest.beans.NewsList;
import com.example.webmusictest.beans.RBody;
import com.example.webmusictest.beans.ResBody;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by King on 2017/6/14.
 */

public class ParseJsonWithGson {

    public static List<NewsItem> parseDailyNewsJson(String jsonData){
        Gson gson = new Gson();
        DailyNews resList = gson.fromJson(jsonData, DailyNews.class);
        ResBody resBody = resList.getShowapi_res_body();
        RBody rBody = resBody.getShowapi_res_body();
        List<NewsList> newsList = rBody.getNewsLists();
        List<NewsItem> newsItemList = new ArrayList<>();
        for (NewsList list : newsList) {
            NewsItem i = new NewsItem();
            i.setTitle(list.getTitle());
            i.setDay(list.getDay());
            i.setUrl(list.getUrl());
            newsItemList.add(i);
        }
        return newsItemList;
    }
}
