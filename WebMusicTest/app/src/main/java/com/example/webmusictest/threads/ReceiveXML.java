package com.example.webmusictest.threads;



import com.example.webmusictest.beans.DailyNews.NewsItem;
import com.example.webmusictest.utils.ParseJsonWithGson;
import com.show.api.ShowApiRequest;

import java.util.List;

/**
 * Created by King on 2017/6/19.
 */

public class ReceiveXML extends Thread {

    private String res;
    private String url;

    public ReceiveXML(String url){
        this.url = url;
    }

    public void run() {
        String appid = "39525";
        String secret = "7003fc66ab7443b3958e700508437360";
        res = new ShowApiRequest(url, appid, secret)
                .post();
//        this.data = ParseJsonWithGson.parseDailyNewsJson(res);
    }

    public String  getRes() {
        return res;
    }
}
