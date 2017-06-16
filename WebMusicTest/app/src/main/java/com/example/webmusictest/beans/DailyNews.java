package com.example.webmusictest.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by King on 2017/6/14.
 */

public class DailyNews {
    private int showapi_res_code;
    private String showapi_res_error;
    private ResBody showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public ResBody getShowapi_res_body() {
        return showapi_res_body;
    }
}