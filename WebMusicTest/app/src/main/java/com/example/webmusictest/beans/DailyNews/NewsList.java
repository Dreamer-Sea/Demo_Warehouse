package com.example.webmusictest.beans.DailyNews;

        import com.google.gson.annotations.SerializedName;

/**
 * Created by King on 2017/6/14.
 */

public class NewsList {private String title;
    @SerializedName("long")
    private String url;
    private String day;

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDay() {
        return day;
    }
}
