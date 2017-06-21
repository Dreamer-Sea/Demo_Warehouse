package com.example.webmusictest.beans.DailyNews;

import com.example.webmusictest.beans.DailyNews.RBody;

/**
 * Created by King on 2017/6/14.
 */

public class ResBody {
    private String showapi_res_error;
    private int showapi_res_code;
    private String msg;
    private RBody showapi_res_body;

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public String getMsg() {
        return msg;
    }

    public RBody getShowapi_res_body() {
        return showapi_res_body;
    }
}
