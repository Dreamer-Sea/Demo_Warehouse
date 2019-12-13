package com.example.webmusictest.threads;

import com.show.api.ShowApiRequest;

/**
 * Created by King on 2017/6/19.
 */

public class ReceiveJSON extends Thread {

    private String res;
    private String url;

    public ReceiveJSON(String url){
        this.url = url;
    }

    public void run() {
        String appid = "39525";
        String secret = "7003fc66ab7443b3958e700508437360";
        res = new ShowApiRequest(url, appid, secret)
                .post();
    }

    public String  getRes() {
        return res;
    }
}
