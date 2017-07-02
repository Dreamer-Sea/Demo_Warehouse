package com.example.webmusictest.utils;

import com.example.webmusictest.beans.BingPicture.BingPicture;
import com.example.webmusictest.beans.BingPicture.Picture;
import com.example.webmusictest.beans.BingPicture.PictureItem;
import com.example.webmusictest.beans.DailyNews.DailyNews;
import com.example.webmusictest.beans.DailyNews.NewsItem;
import com.example.webmusictest.beans.DailyNews.NewsList;
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
        com.example.webmusictest.beans.DailyNews.ResBody resBody = resList.getShowapi_res_body();
        com.example.webmusictest.beans.DailyNews.RBody rBody = resBody.getShowapi_res_body();
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

    public static List<PictureItem> parseBingPictureJson(String jsonData){
        Gson gson = new Gson();
        BingPicture resList = gson.fromJson(jsonData, BingPicture.class);
        com.example.webmusictest.beans.BingPicture.ResBody resBody = resList.getShowapi_res_body();
        List<Picture> pictures = resBody.getList();
        List<PictureItem> pictureItems = new ArrayList<>();
        for (Picture p : pictures) {
            PictureItem i = new PictureItem();
            i.setTitle(p.getTitle());
            i.setPic(p.getPic());
            pictureItems.add(i);
        }
        return pictureItems;
    }
}
