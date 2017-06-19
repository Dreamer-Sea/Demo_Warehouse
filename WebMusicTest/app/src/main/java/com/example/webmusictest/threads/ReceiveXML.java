package com.example.webmusictest.threads;



import com.example.webmusictest.beans.NewsItem;
import com.example.webmusictest.utils.ParseJsonWithGson;
import com.show.api.ShowApiRequest;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by King on 2017/6/19.
 */

public class ReceiveXML extends Thread {

    private List<NewsItem> data;

    @Override
    public void run() {
        String appid = "39525";
        String secret = "7003fc66ab7443b3958e700508437360";
        final String res = new ShowApiRequest("http://route.showapi.com/1071-1", appid, secret)
                .post();
        this.data = ParseJsonWithGson.parseDailyNewsJson(res);
    }

    public List<NewsItem> getData() {
        return data;
    }
}
